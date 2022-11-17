package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.toDomain
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.GetCategoryRepository
import neuro.expenses.register.domain.repository.GetProductRepository

class GetProductRepositoryImpl(
  private val productDao: ProductDao, private val getCategoryRepository: GetCategoryRepository
) : GetProductRepository {
  override fun getProduct(productId: Long): Maybe<ProductDto> {
    return productDao.getProduct(productId).map { roomProduct -> roomProduct.toDomain() }
  }

  override fun getProduct(descriptionLowercase: String): Maybe<ProductDto> {
    return productDao.getProduct(descriptionLowercase).map(RoomProduct::toDomain)
  }
}