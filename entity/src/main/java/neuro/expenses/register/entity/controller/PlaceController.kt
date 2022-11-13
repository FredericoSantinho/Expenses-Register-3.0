package neuro.expenses.register.entity.controller

import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product

interface PlaceController {
  fun addProduct(place: Place, product: Product): Place
  fun removeProduct(place: Place, product: Product): Place
  fun updateProduct(place: Place, product: Product): Place
}