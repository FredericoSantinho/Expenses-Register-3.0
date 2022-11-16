package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetProductRepository {
  fun getProduct(productId: Long): Maybe<PlaceProductDto>
  fun getProduct(
    description: String,
    category: String,
    price: Double,
    placeId: Long
  ): Maybe<PlaceProductDto>
}