package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveProductRepositoryImpl(private val productDao: ProductDao) : SaveProductRepository {
  override fun saveProduct(
    description: String,
    categoryId: Long,
    price: Double,
    defaultAmount: Double
  ): Long {
    return productDao.insert(description, categoryId, price, defaultAmount)
  }
}