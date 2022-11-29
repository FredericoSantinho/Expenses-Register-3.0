package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Place
import neuro.expenses.register.entity.model.PlaceProduct
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct

class PlaceControllerImpl(
  private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct,
  private val addPlaceProduct: AddPlaceProduct,
  private val removePlaceProduct: RemovePlaceProduct
) : PlaceController {
  override fun contains(place: Place, placeProduct: PlaceProduct): Boolean {
    return place.placeProducts.contains(placeProduct)
  }

  override fun addPlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return Single.defer {
      if (!contains(place, placeProduct)) {
        val placeProducts = mutableListOf<PlaceProduct>()
        placeProducts.addAll(place.placeProducts)
        placeProducts.add(placeProduct)
        val newPlace = Place(place.id, place.name, placeProducts, place.latLng)
        addPlaceProduct.addPlaceProduct(place.id, placeProduct.id).toSingle { newPlace }
      } else {
        Single.just(place)
      }
    }
  }

  override fun removePlaceProduct(place: Place, placeProductId: Long): Single<Place> {
    return removePlaceProduct.removePlaceProduct(place.id, placeProductId).toSingle {
      Place(
        place.id, place.name, removeProduct(place.placeProducts, placeProductId), place.latLng
      )
    }
  }

  override fun updatePlaceProduct(place: Place, placeProduct: PlaceProduct): Single<Place> {
    return removePlaceProduct(place, placeProduct.id).flatMap { newPlace ->
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        placeProduct.product.description,
        placeProduct.category.name,
        placeProduct.price,
        placeProduct.product.variableAmount,
        placeProduct.product.iconUrl
      ).flatMap { newPlaceProduct ->
        addPlaceProduct(
          newPlace, newPlaceProduct
        )
      }
    }
  }

  private fun removeProduct(
    placeProducts: List<PlaceProduct>, placeProductId: Long
  ): List<PlaceProduct> {
    return placeProducts.filter { it.id != placeProductId }
  }
}