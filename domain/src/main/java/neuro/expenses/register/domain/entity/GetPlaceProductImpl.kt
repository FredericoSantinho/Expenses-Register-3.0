package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.product.GetPlaceProductRepository
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.GetPlaceProduct

class GetPlaceProductImpl(private val getPlaceProductRepository: GetPlaceProductRepository) :
  GetPlaceProduct {
  override fun getPlaceProduct(
    description: String, category: String, price: Double
  ): Maybe<PlaceProduct> {
    return getPlaceProductRepository.getPlaceProduct(description, category, price)
      .map { it.toEntity() }
  }
}