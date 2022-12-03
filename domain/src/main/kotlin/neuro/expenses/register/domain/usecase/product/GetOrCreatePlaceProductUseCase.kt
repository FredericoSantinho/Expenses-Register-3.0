package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetOrCreatePlaceProductUseCase {
  fun getOrCreatePlaceProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<PlaceProductDto>
}