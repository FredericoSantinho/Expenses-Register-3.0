package neuro.expenses.register.mocks.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.ui.common.composables.appbar.suggestions.place.PlaceSearchSuggestion

fun placeSearchSuggestionsMock(): List<PlaceSearchSuggestion> {
  val list = mutableListOf<PlaceSearchSuggestion>()
  list.add(PlaceSearchSuggestion(1, "Bitoque", {}))
  list.add(PlaceSearchSuggestion(2, "Vizinha", {}))
  list.add(PlaceSearchSuggestion(3, "Longo Bar", {}))
  return list
}

fun createPlaceSearchSuggestionsStateMock(): MutableState<List<PlaceSearchSuggestion>> {
  return mutableStateOf(placeSearchSuggestionsMock())
}
