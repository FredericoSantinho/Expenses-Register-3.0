package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

interface SortPlaceProducts {
  /**
   * Sort place products by their description.
   *
   * @param placeProductsDtos list of Place Products to sort.
   * @return Single with a list of sorted Place Products.
   */
  fun sortPlaceProducts(placeProductsDtos: List<PlaceProductDto>): Single<List<PlaceProductDto>>
}