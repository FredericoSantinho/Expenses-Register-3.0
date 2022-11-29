package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.PlaceProduct

interface GetOrCreatePlaceProduct {
  /**
   * Get or create PlaceProduct (and Product if needed) if it doesn't exist based on its
   * description, category and price.
   *
   * variableAmount and iconUrl will be used if Product doesn't exist, otherwise they will be
   * ignored.
   *
   * @param description description.
   * @param category category.
   * @param price price.
   * @param variableAmount variableAmount.
   * @param iconUrl iconUrl.
   * @return Single with the PlaceProduct.
   */
  fun getOrCreatePlaceProduct(
    description: String, category: String, price: Double, variableAmount: Boolean, iconUrl: String
  ): Single<PlaceProduct>
}