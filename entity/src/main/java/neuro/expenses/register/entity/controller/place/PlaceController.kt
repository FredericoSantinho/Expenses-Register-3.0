package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct

interface PlaceController {
  fun contains(place: Place, placeProduct: PlaceProduct): Boolean
  fun addPlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
  fun removePlaceProduct(place: Place, placeProductId: Long): Single<Place>
  fun updatePlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
}