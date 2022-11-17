package neuro.expenses.register.ui.edit.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.edit.Directions

class EditDestinationsMapper

@StringRes
fun Directions.toPresentation(): Int = when (this) {
  Directions.product -> R.string.title_edit_product
  Directions.placeProduct -> R.string.title_edit_place_product
  Directions.category -> R.string.title_edit_category
  Directions.place -> R.string.title_edit_place
}