package neuro.expenses.register.ui.common.composables.appbar.suggestions.place

import androidx.compose.runtime.Composable
import neuro.expenses.register.ui.common.composables.search.SearchSuggestion
import neuro.expenses.register.viewmodel.common.filter.DefaultStringFilter
import neuro.expenses.register.viewmodel.common.filter.StringFilter

class PlaceSearchSuggestion(
  private val id: Long,
  private val name: String,
  private val onPlaceSearchSuggestion: (Long) -> (Unit),
  private val stringFilter: StringFilter = DefaultStringFilter()
) : SearchSuggestion {
  override fun filter(value: String): Boolean {
    return stringFilter.filter(value, name)
  }

  override fun text(): String {
    return name
  }

  @Composable
  override fun composable() {
    PlaceSearchSuggestionComposable(name)
  }

  @Composable
  override fun titleComposable() {
    ProductSearchSuggestionTitleComposable()
  }

  override fun onClick() {
    onPlaceSearchSuggestion(id)
  }
}