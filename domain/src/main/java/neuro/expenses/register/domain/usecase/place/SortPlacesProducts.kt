package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.entity.Place

interface SortPlacesProducts {
  fun sortPlacesProducts(places: List<Place>): List<Place>
}