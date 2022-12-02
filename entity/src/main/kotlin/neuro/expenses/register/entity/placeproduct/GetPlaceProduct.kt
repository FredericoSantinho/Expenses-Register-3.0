package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.PlaceProduct

interface GetPlaceProduct {
  /**
   * Get PlaceProduct if it exists based on its description, category and price.
   * @param description description.
   * @param category category.
   * @param price price.
   * @return Maybe with PlaceProduct or empty if no PlaceProduct with the given arguments exists.
   */
  fun getPlaceProduct(
    description: String,
    category: String,
    price: Double
  ): Maybe<PlaceProduct>
}