package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.GetProductRepository
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.product.GetProduct

class GetProductImpl(
  private val getProductRepository: GetProductRepository
) : GetProduct {
  override fun getProduct(description: String): Maybe<Product> {
    return getProductRepository.getProduct(description).map(ProductDto::toEntity)
  }
}