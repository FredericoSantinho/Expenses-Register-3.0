package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Product

interface GetProduct {
  /**
   * Get Product.
   *
   * @param description description.
   * @return Maybe with Product or empty if no Product with the given description exists.
   */
  fun getProduct(description: String): Maybe<Product>
}