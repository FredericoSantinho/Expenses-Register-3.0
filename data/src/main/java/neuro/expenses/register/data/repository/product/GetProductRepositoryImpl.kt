package neuro.expenses.register.data.repository.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.toDomain
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.product.GetProductRepository

class GetProductRepositoryImpl(
  private val productDao: ProductDao
) : GetProductRepository {

  override fun getProduct(description: String): Maybe<ProductDto> {
    return productDao.getProduct(description.lowercase()).map(RoomProduct::toDomain)
  }
}