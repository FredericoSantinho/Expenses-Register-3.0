package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.toData
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.repository.SavePlaceRepository

class SavePlaceRepositoryImpl(private val placeDao: PlaceDao) : SavePlaceRepository {
  override fun savePlace(placeDto: PlaceDto): Completable {
    return Single.just(placeDto.toData()).flatMapCompletable { roomPlace ->
      placeDao.insert(roomPlace).flatMapObservable {
        Observable.fromIterable(placeDto.products).flatMapSingle { productDto ->
          placeDao.insert(
            PlacePlaceProductCrossRef(
              placeDto.id,
              productDto.id
            )
          )
        }
      }.ignoreElements().andThen(
        placeDao.getAllCrossRef(placeDto.id)
          .flattenAsObservable { placePlaceProductCrossRefs -> placePlaceProductCrossRefs }
          .filter { placePlaceProductCrossRef ->
            !contains(
              placeDto.products,
              placePlaceProductCrossRef.placeProductId
            )
          }
          .flatMapCompletable { placePlaceProductCrossRef ->
            placeDao.delete(
              placePlaceProductCrossRef
            )
          })
    }
  }

  private fun contains(products: List<PlaceProductDto>, placeProductId: Long): Boolean {
    return products.any { it.id == placeProductId }
  }
}