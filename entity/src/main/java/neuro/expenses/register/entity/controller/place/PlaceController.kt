package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product

interface PlaceController {
  fun addProduct(place: Place, product: Product): Single<Place>
  fun removeProduct(place: Place, product: Product): Single<Place>
  fun updateProduct(place: Place, product: Product): Single<Place>
}