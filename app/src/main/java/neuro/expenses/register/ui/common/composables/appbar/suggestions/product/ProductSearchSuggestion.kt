package neuro.expenses.register.ui.common.composables.appbar.suggestions.product

import androidx.compose.runtime.Composable
import neuro.expenses.register.ui.common.composables.search.SearchSuggestion
import neuro.expenses.register.viewmodel.common.filter.DefaultStringFilter
import neuro.expenses.register.viewmodel.common.filter.StringFilter

class ProductSearchSuggestion(
  private val description: String,
  private val price: String,
  private val iconUrl: String,
  private val stringFilter: StringFilter = DefaultStringFilter()
) : SearchSuggestion {
  override fun filter(value: String): Boolean {
    return stringFilter.filter(value, description)
  }

  override fun text(): String {
    return description
  }

  @Composable
  override fun composable() {
    ProductSearchSuggestionComposable(description, price, iconUrl)
  }

  @Composable
  override fun titleComposable() {
    ProductSearchSuggestionTitleComposable()
  }
}