package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.domain.repository.GetProductRepository
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.product.GetProduct

class GetProductImpl(
  private val getProductRepository: GetProductRepository,
  private val productMapper: ProductMapper
) : GetProduct {
  override fun getProduct(description: String, category: String, price: Double): Maybe<Product> {
    return getProductRepository.getProduct(description, category, price)
      .map { productMapper.map(it) }
  }
}