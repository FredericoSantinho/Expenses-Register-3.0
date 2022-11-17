package neuro.expenses.register.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.ui.common.composables.dropdown.DropdownButton
import neuro.expenses.register.ui.common.composables.dropdown.OnItemSelect
import neuro.expenses.register.ui.edit.category.EditCategoriesComposable
import neuro.expenses.register.ui.edit.mapper.toPresentation
import neuro.expenses.register.ui.edit.place.EditPlacesComposable
import neuro.expenses.register.ui.edit.placeproduct.EditPlaceProductsComposable
import neuro.expenses.register.ui.edit.product.EditProductsComposable
import neuro.expenses.register.viewmodel.edit.Directions
import neuro.expenses.register.viewmodel.edit.EditViewModel
import neuro.expenses.register.viewmodel.edit.UiEvent
import org.koin.androidx.compose.getViewModel

@Composable
fun EditComposable(editViewModel: EditViewModel = getViewModel()) {
  val uiEvent by editViewModel.uiEvent.observeAsState(null)

  val navController = rememberNavController()

  onUiEvent(uiEvent, navController)

  Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end = 4.dp)) {
    DropdownButton(
      modifier = Modifier.width(160.dp),
      items = editViewModel.getItems().map { it.toPresentation() }.map { stringResource(it) },
      onItemSelect = object : OnItemSelect {
        override fun onItemSelect(index: Int) {
          editViewModel.onOptionSelected(index)
        }
      })
    NavHost(
      navController = navController,
      startDestination = Directions.product.toString(),
      modifier = Modifier.fillMaxSize()
    ) {
      composable(Directions.product.toString()) { EditProductsComposable() }
      composable(Directions.placeProduct.toString()) { EditPlaceProductsComposable() }
      composable(Directions.category.toString()) { EditCategoriesComposable() }
      composable(Directions.place.toString()) { EditPlacesComposable() }
    }
  }
}

@Composable
private fun onUiEvent(uiEvent: UiEvent?, navController: NavController) {
  when (uiEvent) {
    UiEvent.NavigateToEditProduct -> navigate(navController, Directions.product)
    UiEvent.NavigateToEditPlaceProduct -> navigate(navController, Directions.placeProduct)
    UiEvent.NavigateToEditCategory -> navigate(navController, Directions.category)
    UiEvent.NavigateToEditPlace -> navigate(navController, Directions.place)
    null -> {}
  }
}

@Composable
private fun navigate(navController: NavController, directions: Directions) {
  navController.navigate(directions.toString()) {
    popUpTo(0)
  }
}