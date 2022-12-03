package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto

interface GetProductRepository {
  fun getProduct(description: String): Maybe<ProductDto>
}