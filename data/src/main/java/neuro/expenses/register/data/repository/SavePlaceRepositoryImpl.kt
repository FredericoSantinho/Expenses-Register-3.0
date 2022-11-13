package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.RoomPlaceMapper
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.SavePlaceRepository
import neuro.expenses.register.domain.repository.SaveProductRepository

class SavePlaceRepositoryImpl(
  private val placeDao: PlaceDao,
  private val saveProductRepository: SaveProductRepository,
  private val roomPlaceMapper: RoomPlaceMapper
) : SavePlaceRepository {
  override fun savePlace(placeDto: PlaceDto): Completable {
    return Single.just(roomPlaceMapper.map(placeDto)).flatMapObservable { roomPlace ->
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
    }.ignoreElements()
  }
}