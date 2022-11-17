package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct

class SortPlaceProductsImpl : SortPlaceProducts {
  override fun sort(places: List<Place>): List<Place> {
    return places.map { Place(it.id, it.name, map(it.placeProducts), it.latLng) }
  }

  private fun map(placeProducts: List<PlaceProduct>): List<PlaceProduct> {
    return placeProducts.sortedBy { it.product.description }
  }
}