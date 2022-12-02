package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Place
import neuro.expenses.register.entity.model.PlaceProduct

interface PlaceController {
  /**
   * Check if a Place contains a PlaceProduct.
   *
   * @param place Place.
   * @param placeProduct PlaceProduct.
   * @return true if Place contains the given PlaceProduct, false otherwise.
   */
  fun contains(place: Place, placeProduct: PlaceProduct): Boolean

  /**
   * Add PlaceProduct to a Place.
   * @param place Place.
   * @param placeProduct PlaceProduct.
   * @return Single with the updated Place.
   */
  fun addPlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place>

  /**
   * Remove PlaceProduct from a Place.
   * @param place Place.
   * @param placeProductId PlaceProduct id.
   * @return Single with the updated Place.
   */
  fun removePlaceProduct(place: Place, placeProductId: Long): Single<Place>

  /**
   * Update PlaceProduct in a Place.
   * @param place Place.
   * @param placeProduct PlaceProduct.
   * @return Single with the updated Place.
   */
  fun updatePlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
}