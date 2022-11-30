package neuro.expenses.register.presentation.ui.edit.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.ui.edit.ViewPagerPage
import neuro.expenses.register.presentation.ui.edit.category.EditCategoriesComposable
import neuro.expenses.register.viewmodel.edit.EditUiEvent

@Composable
fun EditUiEvent.Destination.toPresentation(): ViewPagerPage {
  val title = getDirectionTitle(this)
  return when (this) {
    EditUiEvent.Destination.categories -> ViewPagerPage(title) { EditCategoriesComposable() }
  }
}

@Composable
private fun getDirectionTitle(destination: EditUiEvent.Destination): String {
  return when (destination) {
    EditUiEvent.Destination.categories -> stringResource(R.string.edit_categories_title)
  }
}
