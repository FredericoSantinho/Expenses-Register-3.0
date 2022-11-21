package neuro.expenses.register.ui.common.mapper

import neuro.expenses.register.ui.common.composables.appbar.suggestions.place.PlaceSearchSuggestion
import neuro.expenses.register.ui.common.composables.appbar.suggestions.product.ProductSearchSuggestion
import neuro.expenses.register.ui.common.composables.search.SearchSuggestion
import neuro.expenses.register.viewmodel.model.search.PlaceSearchSuggestionModel
import neuro.expenses.register.viewmodel.model.search.ProductSearchSuggestionModel


fun neuro.expenses.register.viewmodel.model.search.SearchSuggestion.toPresentation(): SearchSuggestion {
  return when (this) {
    is ProductSearchSuggestionModel -> ProductSearchSuggestion(description, price, iconUrl)
    is PlaceSearchSuggestionModel -> PlaceSearchSuggestion(id, name, onPlaceSearchSuggestion)
    else -> {
      throw java.lang.IllegalArgumentException("Mapping function not implemented for class ${this.javaClass}!")
    }
  }
}
