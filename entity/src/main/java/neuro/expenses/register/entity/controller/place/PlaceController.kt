package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct

interface PlaceController {
  fun contains(place: Place, placeProduct: PlaceProduct): Boolean
  fun addProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
  fun removeProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
  fun updateProduct(place: Place, placeProduct: PlaceProduct): Single<Place>
}