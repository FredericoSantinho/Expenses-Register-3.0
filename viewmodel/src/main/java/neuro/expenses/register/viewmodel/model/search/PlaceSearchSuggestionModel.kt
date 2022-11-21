package neuro.expenses.register.viewmodel.model.search

data class PlaceSearchSuggestionModel(
  val id: Long, val name: String, val onPlaceSearchSuggestion: (Long) -> (Unit)
) : SearchSuggestion