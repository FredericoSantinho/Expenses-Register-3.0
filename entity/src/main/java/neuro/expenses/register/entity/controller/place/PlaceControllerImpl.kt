package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.product.GetOrCreateProduct

class PlaceControllerImpl(private val getOrCreateProduct: GetOrCreateProduct) : PlaceController {
  override fun addProduct(place: Place, product: Product): Single<Place> {
    return getOrCreateProduct.getOrCreateProduct(product, place.name).map { savedProduct ->
      val products = mutableListOf<Product>()
      products.addAll(place.products)
      products.add(savedProduct)
      Place(place.id, place.name, products, place.latLng)
    }
  }

  override fun removeProduct(place: Place, product: Product): Single<Place> {
    return Single.fromCallable { place.products.filter { it.id != product.id } }
      .map { products -> Place(place.id, place.name, products, place.latLng) }
  }

  override fun updateProduct(place: Place, product: Product): Single<Place> {
    return removeProduct(place, product).flatMap { newPlace -> addProduct(newPlace, product) }
  }
}