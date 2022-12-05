package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto

interface GetProductRepository {
  /**
   * Get Product.
   *
   * @param description description.
   * @return Maybe with Product or empty if no Product with the given description exists.
   */
  fun getProduct(description: String): Maybe<ProductDto>
}