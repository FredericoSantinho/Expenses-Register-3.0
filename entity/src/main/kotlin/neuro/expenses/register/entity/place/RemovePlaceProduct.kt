package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProduct {
  /**
   * Remove PlaceProduct from Place.
   *
   * @param placeId Place id.
   * @param placeProductId PlaceProduct id.
   * @return Completable that completes if operation succeeds.
   */
  fun removePlaceProduct(placeId: Long, placeProductId: Long): Completable
}