package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Completable

interface AddPlaceProductRepository {
  /**
   * Add a Place Product to a Place.
   *
   * @param placeId place id.
   * @param placeProductId placeProductId id.
   * @return Completable that completes if operation succeeds.
   */
  fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable
}