package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct

class GetOrCreatePlaceProductUseCaseImpl(private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct) :
  GetOrCreatePlaceProductUseCase {
  override fun getOrCreatePlaceProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<PlaceProductDto> {
    return getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      description,
      category,
      price,
      variableAmount,
      iconUrl
    ).map { it.toDomain() }
  }
}