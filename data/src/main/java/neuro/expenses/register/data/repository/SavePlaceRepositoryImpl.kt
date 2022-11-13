package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.RoomPlaceMapper
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.GetProductRepository
import neuro.expenses.register.domain.repository.SavePlaceRepository
import neuro.expenses.register.domain.repository.SaveProductRepository

class SavePlaceRepositoryImpl(
  private val placeDao: PlaceDao,
  private val saveProductRepository: SaveProductRepository,
  private val getProductRepository: GetProductRepository,
  private val roomPlaceMapper: RoomPlaceMapper
) : SavePlaceRepository {
  override fun savePlace(placeDto: PlaceDto): Completable {
    return placeDao.getAllCrossRef(placeDto.name.lowercase()).flatMapCompletable { crossRefs ->
      Single.just(roomPlaceMapper.map(placeDto)).flatMapObservable { roomPlace ->
        placeDao.insert(roomPlace).flatMapObservable {
          Observable.fromIterable(placeDto.products).map { productDto ->
            saveProductRepository.saveProduct(productDto)
          }
        }.flatMapSingle { productId ->
          placeDao.insert(
            PlacePlaceProductCrossRef(
              placeDto.name.lowercase(),
              productId
            )
          )
        }
      }.ignoreElements().andThen(
        Observable.fromIterable(crossRefs).flatMapCompletable { placePlaceProductCrossRef ->
          getProductRepository.getProduct(placePlaceProductCrossRef.placeProductId)
            .filter { !contains(placeDto.products, it) }.flatMapCompletable {
              placeDao.delete(placePlaceProductCrossRef)
            }

        })
    }
  }

  private fun contains(products: List<ProductDto>, productDto: ProductDto): Boolean {
    return products.any { it.equals(productDto) }
  }

  private fun contains(products: List<ProductDto>, placeProductId: Long): Boolean {
    return products.any { it.id == placeProductId }
  }
}