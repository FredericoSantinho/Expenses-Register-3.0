package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.PlaceProduct

interface SavePlaceProduct {
  /**
   * Save PlaceProduct.
   *
   * @param placeProduct PlaceProduct to save.
   * @return Completable that completes if operation succeeds.
   */
  fun savePlaceProduct(placeProduct: PlaceProduct): Completable
}