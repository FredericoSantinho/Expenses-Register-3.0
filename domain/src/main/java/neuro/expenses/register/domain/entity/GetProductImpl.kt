package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.GetProductRepository
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.product.GetProduct

class GetProductImpl(
  private val getProductRepository: GetProductRepository
) : GetProduct {
  override fun getProduct(
    description: String, category: String, price: Double, place: Place
  ): Maybe<Product> {
    return getProductRepository.getProduct(description, category, price, place.id)
      .map { it.toEntity() }
  }
}