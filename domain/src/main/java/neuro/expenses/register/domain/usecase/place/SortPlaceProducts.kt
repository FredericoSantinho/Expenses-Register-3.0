package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.entity.Place

interface SortPlaceProducts {
  fun sort(places: List<Place>): List<Place>
}