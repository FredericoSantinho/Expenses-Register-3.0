package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.PlaceProduct

interface GetPlaceProduct {
  /**
   * Get Place Product.
   * @param description description.
   * @param category category.
   * @param price price.
   * @return Maybe with Place Product or empty if no PlaceProduct with the given arguments exists.
   */
  fun getPlaceProduct(
    description: String,
    category: String,
    price: Double
  ): Maybe<PlaceProduct>
}