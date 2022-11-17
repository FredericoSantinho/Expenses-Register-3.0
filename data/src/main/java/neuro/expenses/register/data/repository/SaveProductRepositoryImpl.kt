package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.toData
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveProductRepositoryImpl(private val productDao: ProductDao) : SaveProductRepository {
  override fun saveProduct(productDto: ProductDto): Completable {
    return productDao.insert(productDto.toData()).ignoreElement()
  }
}