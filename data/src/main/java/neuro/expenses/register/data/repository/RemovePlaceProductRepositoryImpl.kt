package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.repository.RemovePlaceProductRepository

class RemovePlaceProductRepositoryImpl(private val placeDao: PlaceDao) :
  RemovePlaceProductRepository {
  override fun removePlaceProduct(placeId: Long, productId: Long): Completable {
    return placeDao.delete(PlacePlaceProductCrossRef(placeId, productId))
  }
}