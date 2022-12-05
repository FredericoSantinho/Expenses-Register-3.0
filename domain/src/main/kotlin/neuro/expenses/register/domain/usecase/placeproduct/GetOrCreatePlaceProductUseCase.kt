package neuro.expenses.register.domain.usecase.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetOrCreatePlaceProductUseCase {
  /**
   * Get or create Place Product (and Product if needed) if it doesn't exist based on its
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
   * @return Single with the Place Product.
   */
  fun getOrCreatePlaceProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<PlaceProductDto>
}