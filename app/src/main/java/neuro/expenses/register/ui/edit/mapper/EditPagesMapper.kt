package neuro.expenses.register.ui.edit.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import neuro.expenses.register.R
import neuro.expenses.register.ui.edit.ViewPagerPage
import neuro.expenses.register.ui.edit.category.EditCategoriesComposable
import neuro.expenses.register.ui.edit.place.EditPlacesComposable
import neuro.expenses.register.ui.edit.placeproduct.EditPlaceProductsComposable
import neuro.expenses.register.ui.edit.product.EditProductsComposable
import neuro.expenses.register.viewmodel.edit.EditUiEvent

@Composable
fun EditUiEvent.Destination.toPresentation(): ViewPagerPage {
  val title = getDirectionTitle(this)
  return when (this) {
    EditUiEvent.Destination.categories -> ViewPagerPage(title) { EditCategoriesComposable() }
    EditUiEvent.Destination.products -> ViewPagerPage(title) { EditProductsComposable() }
    EditUiEvent.Destination.placeProducts -> ViewPagerPage(title) { EditPlaceProductsComposable() }
    EditUiEvent.Destination.places -> ViewPagerPage(title) { EditPlacesComposable() }
  }
}

@Composable
private fun getDirectionTitle(destination: EditUiEvent.Destination): String {
  return when (destination) {
    EditUiEvent.Destination.categories -> stringResource(R.string.edit_categories_title)
    EditUiEvent.Destination.products -> stringResource(R.string.edit_products_title)
    EditUiEvent.Destination.placeProducts -> stringResource(R.string.edit_place_products_title)
    EditUiEvent.Destination.places -> stringResource(R.string.edit_places_title)
  }
}
