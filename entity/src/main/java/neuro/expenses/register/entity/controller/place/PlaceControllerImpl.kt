package neuro.expenses.register.entity.controller.place

import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product

class PlaceControllerImpl() : PlaceController {
  override fun addProduct(place: Place, product: Product): Place {
    val products = mutableListOf<Product>()
    products.addAll(place.products)
    products.add(product)
    return Place(place.id, place.name, products, place.latLng)
  }

  override fun removeProduct(place: Place, product: Product): Place {
    val products = place.products.filter { it.id != product.id }
    return Place(place.id, place.name, products, place.latLng)
  }

  override fun updateProduct(place: Place, product: Product): Place {
    val newPlace = removeProduct(place, product)
    return addProduct(newPlace, product)
  }
}