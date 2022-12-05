package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProductRepository {
  /**
   * Remove Place Product from Place.
   *
   * @param placeId Place id.
   * @param placeProductId PlaceProduct id.
   * @return Completable that completes if operation succeeds.
   */
  fun removePlaceProduct(placeId: Long, placeProductId: Long): Completable
}