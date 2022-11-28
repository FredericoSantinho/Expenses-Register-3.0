package neuro.expenses.register.data.repository.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceProductDao
import neuro.expenses.register.domain.repository.product.GeneratePlaceProductIdRepository

class GeneratePlaceProductIdRepositoryImpl(private val placeProductDao: PlaceProductDao) :
  GeneratePlaceProductIdRepository {
  override fun newId(): Single<Long> {
    return placeProductDao.getLastPlaceProductId().defaultIfEmpty(0).map { it + 1 }
  }
}