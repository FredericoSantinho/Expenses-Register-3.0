package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.placeproduct.GetPlaceProductRepository
import neuro.expenses.register.entity.model.PlaceProduct
import neuro.expenses.register.entity.placeproduct.GetPlaceProduct

class GetPlaceProductImpl(private val getPlaceProductRepository: GetPlaceProductRepository) :
  GetPlaceProduct {
  override fun getPlaceProduct(
    description: String, category: String, price: Double
  ): Maybe<PlaceProduct> {
    return getPlaceProductRepository.getPlaceProduct(description, category, price)
      .map { it.toEntity() }
  }
}