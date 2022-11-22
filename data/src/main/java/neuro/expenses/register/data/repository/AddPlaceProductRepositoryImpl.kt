package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.repository.AddPlaceProductRepository

class AddPlaceProductRepositoryImpl(private val placeDao: PlaceDao) : AddPlaceProductRepository {
  override fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable {
    return placeDao.insert(PlacePlaceProductCrossRef(placeId, placeProductId)).ignoreElement()
  }
}