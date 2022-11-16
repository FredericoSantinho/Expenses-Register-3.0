package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto

interface GetProductRepository {
  fun getProduct(productId: Long): Maybe<ProductDto>
  fun getProduct(
    description: String,
    category: String,
    price: Double,
    placeId: Long
  ): Maybe<ProductDto>
}