package neuro.expenses.register.domain.repository.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetPlaceProductRepository {
  /**
   * Get Place Product.
   * @param description description.
   * @param category category.
   * @param price price.
   * @return Maybe with PlaceProduct or empty if no PlaceProduct with the given arguments exists.
   */
  fun getPlaceProduct(description: String, category: String, price: Double): Maybe<PlaceProductDto>
}