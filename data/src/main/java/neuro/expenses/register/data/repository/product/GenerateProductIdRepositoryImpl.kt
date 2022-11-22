package neuro.expenses.register.data.repository.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.domain.repository.product.GenerateProductIdRepository

class GenerateProductIdRepositoryImpl(private val productDao: ProductDao) :
  GenerateProductIdRepository {
  override fun newId(): Single<Long> {
    return productDao.getLastProductId().defaultIfEmpty(0).map { it + 1 }
  }
}