package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetPlaceProductRepository {
  fun getPlaceProduct(productId: Long): Maybe<PlaceProductDto>
  fun getPlaceProduct(
    description: String, category: String, price: Double
  ): Maybe<PlaceProductDto>
}