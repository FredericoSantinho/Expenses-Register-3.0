package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.GetOrCreatePlaceProduct

class PlaceControllerImpl(
  private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct,
  private val addPlaceProduct: AddPlaceProduct,
  private val removePlaceProduct: RemovePlaceProduct
) :
  PlaceController {
  override fun contains(place: Place, placeProduct: PlaceProduct): Boolean {
    return place.placeProducts.contains(placeProduct)
  }

  override fun addPlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return Single.defer {
      if (!contains(place, placeProduct)) {
        getOrCreatePlaceProduct.getOrCreatePlaceProduct(
          placeProduct.product.description,
          placeProduct.category.name,
          placeProduct.price,
          placeProduct.product.variableAmount
        ).map { savedProduct ->
          val placeProducts = mutableListOf<PlaceProduct>()
          placeProducts.addAll(place.placeProducts)
          placeProducts.add(savedProduct)
          Place(place.id, place.name, placeProducts, place.latLng)
        }.flatMap { newPlace ->
          addPlaceProduct.addPlaceProduct(place.id, placeProduct.id).toSingle { newPlace }
        }
      } else {
        Single.just(place)
      }
    }
  }

  override fun removePlaceProduct(place: Place, placeProductId: Long): Single<Place> {
    return Single.fromCallable { place.placeProducts.filter { it.id != placeProductId } }
      .map { products -> Place(place.id, place.name, products, place.latLng) }
      .flatMap { newPlace ->
        removePlaceProduct.removePlaceProduct(place.id, placeProductId).toSingle { newPlace }
      }
  }

  override fun updatePlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return removePlaceProduct(place, placeProduct.id).flatMap { newPlace ->
      addPlaceProduct(
        newPlace,
        placeProduct
      )
    }
  }
}