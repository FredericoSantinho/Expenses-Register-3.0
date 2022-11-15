package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.GetProductRepository
import neuro.expenses.register.entity.controller.GetProductId

class GetProductIdImpl(private val getProductRepository: GetProductRepository) : GetProductId {
  override fun getProductId(description: String, category: String, price: Double): Maybe<Long> {
    return getProductRepository.getProduct(description, category, price).map { it.id }
  }
}