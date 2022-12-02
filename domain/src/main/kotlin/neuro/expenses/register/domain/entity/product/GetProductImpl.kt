package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.product.GetProductRepository
import neuro.expenses.register.entity.model.Product
import neuro.expenses.register.entity.product.GetProduct

class GetProductImpl(
  private val getProductRepository: GetProductRepository
) : GetProduct {
  override fun getProduct(description: String): Maybe<Product> {
    return getProductRepository.getProduct(description).map(ProductDto::toEntity)
  }
}