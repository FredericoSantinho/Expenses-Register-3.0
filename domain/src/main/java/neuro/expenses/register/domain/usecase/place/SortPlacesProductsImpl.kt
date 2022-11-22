package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct

class SortPlacesProductsImpl : SortPlacesProducts {
  override fun sortPlacesProducts(places: List<Place>): List<Place> {
    return places.map { Place(it.id, it.name, sort(it.placeProducts), it.latLng) }
  }

  private fun sort(placeProducts: List<PlaceProduct>): List<PlaceProduct> {
    return placeProducts.sortedBy { it.product.description }
  }
}