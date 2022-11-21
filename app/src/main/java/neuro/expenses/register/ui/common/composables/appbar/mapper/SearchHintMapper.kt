package neuro.expenses.register.ui.common.composables.appbar.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.appbar.SearchHint
import neuro.expenses.register.viewmodel.home.SearchProductsAndPlaces

@StringRes
fun SearchHint.toPresentation(): Int {
  return when (this) {
    is SearchHint.Search -> R.string.search
    is SearchProductsAndPlaces -> R.string.search_products_and_places
    else -> {
      throw java.lang.IllegalArgumentException("Mapping not implemented for class ${this.javaClass}!")
    }
  }
}