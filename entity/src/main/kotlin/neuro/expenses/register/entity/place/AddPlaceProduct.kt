package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable

interface AddPlaceProduct {
  /**
   * Add a PlaceProduct to a Place.
   *
   * @param placeId place id.
   * @param placeProductId placeProductId id.
   * @return Completable that completes if operation succeeds.
   */
  fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable
}