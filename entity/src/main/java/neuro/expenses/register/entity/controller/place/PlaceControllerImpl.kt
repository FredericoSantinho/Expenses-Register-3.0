package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.GetOrCreatePlaceProduct

class PlaceControllerImpl(
  private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct,
  private val savePlace: SavePlace
) :
  PlaceController {
  override fun contains(place: Place, placeProduct: PlaceProduct): Boolean {
    return place.placeProducts.contains(placeProduct)
  }

  override fun addProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      placeProduct.product.description,
      placeProduct.category.name,
      placeProduct.price,
      placeProduct.product.variableAmount
    ).map { savedProduct ->
      val placeProducts = mutableListOf<PlaceProduct>()
      placeProducts.addAll(place.placeProducts)
      placeProducts.add(savedProduct)
      Place(place.id, place.name, placeProducts, place.latLng)
    }.flatMap { newPlace -> savePlace.savePlace(newPlace).toSingle { newPlace } }
  }

  override fun removeProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return Single.fromCallable { place.placeProducts.filter { it.id != placeProduct.id } }
      .map { products -> Place(place.id, place.name, products, place.latLng) }
      .flatMap { newPlace -> savePlace.savePlace(newPlace).toSingle { newPlace } }
  }

  override fun updateProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return removeProduct(place, placeProduct).flatMap { newPlace ->
      addProduct(
        newPlace,
        placeProduct
      )
    }
  }
}