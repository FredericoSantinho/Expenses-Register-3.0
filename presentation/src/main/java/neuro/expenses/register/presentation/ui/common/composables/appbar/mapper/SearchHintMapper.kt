package neuro.expenses.register.presentation.ui.common.composables.appbar.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.presentation.R
import neuro.expenses.register.viewmodel.appbar.SearchHint
import neuro.expenses.register.viewmodel.home.searchProductsAndPlaces

@StringRes
fun SearchHint.toPresentation(): Int {
  return when (this) {
    is SearchHint.Search -> R.string.search
    is searchProductsAndPlaces -> R.string.search_products_and_places
    else -> {
      throw java.lang.IllegalArgumentException("Mapping not implemented for class ${this.javaClass}!")
    }
  }
}