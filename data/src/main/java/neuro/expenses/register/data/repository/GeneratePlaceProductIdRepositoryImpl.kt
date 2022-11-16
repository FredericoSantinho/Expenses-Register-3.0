package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.domain.repository.GeneratePlaceProductIdRepository

class GeneratePlaceProductIdRepositoryImpl(private val productDao: ProductDao) :
  GeneratePlaceProductIdRepository {
  override fun newId(): Single<Long> {
    return productDao.getLastPlaceProductId().defaultIfEmpty(0).map { it + 1 }
  }
}