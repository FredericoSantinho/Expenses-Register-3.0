package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.GetPlaceProductRepository
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.GetPlaceProduct

class GetPlaceProductImpl(private val getPlaceProductRepository: GetPlaceProductRepository) :
  GetPlaceProduct {
  override fun getPlaceProduct(
    description: String, category: String, price: Double, place: Place
  ): Maybe<PlaceProduct> {
    return getPlaceProductRepository.getPlaceProduct(description, category, price, place.id)
      .map { it.toEntity() }
  }
}