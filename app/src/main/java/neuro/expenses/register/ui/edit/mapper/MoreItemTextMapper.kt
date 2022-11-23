package neuro.expenses.register.ui.edit.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.appbar.MoreItemText
import neuro.expenses.register.viewmodel.edit.EditMoreItem.*

@StringRes
fun MoreItemText.toPresentation(): Int {
  return when (this) {
    is EditProductMoreItem -> R.string.title_edit_products
    is EditPlaceProductMoreItem -> R.string.title_edit_place_products
    is EditCategoryMoreItem -> R.string.title_edit_categories
    is EditPlaceMoreItem -> R.string.title_edit_places
    else -> {
      throw java.lang.IllegalArgumentException("Mapping not implemented for class ${this.javaClass}!")
    }
  }
}