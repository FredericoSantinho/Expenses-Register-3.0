package neuro.expenses.register.domain.repository.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto

interface SavePlaceProductRepository {
  /**
   * Save Place Product.
   *
   * @param placeProduct PlaceProduct to save.
   * @return Completable that completes if operation succeeds.
   */
  fun savePlaceProduct(placeProductDto: PlaceProductDto): Completable
}