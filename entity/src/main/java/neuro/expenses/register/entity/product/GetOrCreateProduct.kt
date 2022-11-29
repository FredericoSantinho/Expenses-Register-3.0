package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Product

interface GetOrCreateProduct {
  /**
   * Get or create Product if it doesn't exist based on its description.
   *
   * variableAmount and iconUrl will be used if Product doesn't exist, otherwise they will be
   * ignored.
   *
   * @param description description.
   * @param variableAmount variableAmount.
   * @param iconUrl iconUrl.
   * @return Single with the Product.
   */
  fun getOrCreateProduct(
    description: String,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<Product>
}