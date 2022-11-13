package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto

interface GetProductUseCase {
  fun getProduct(productId: Long): Maybe<ProductDto>
}