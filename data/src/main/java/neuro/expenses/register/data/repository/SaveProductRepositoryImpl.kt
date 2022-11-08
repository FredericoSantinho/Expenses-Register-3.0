package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveProductRepositoryImpl(private val productDao: ProductDao) : SaveProductRepository {
  override fun saveProduct(
    description: String,
    category: String,
    price: Double,
    amount: Double
  ): Long {
    return productDao.insert(description, category, price, amount)
  }
}