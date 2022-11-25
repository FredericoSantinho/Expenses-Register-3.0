package neuro.expenses.register.ui.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.common.compose.rememberUnit
import neuro.expenses.register.ui.edit.category.EditCategoriesComposable
import neuro.expenses.register.ui.edit.place.EditPlacesComposable
import neuro.expenses.register.ui.edit.placeproduct.EditPlaceProductsComposable
import neuro.expenses.register.ui.edit.product.EditProductsComposable
import neuro.expenses.register.viewmodel.edit.EditUiEvent.Directions
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent
import neuro.expenses.register.viewmodel.edit.EditViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditComposable(editViewModel: EditViewModel = getViewModel()) {
  rememberUnit { editViewModel.onComposition() }

  val uiEvent by editViewModel.uiEvent

  val navController = rememberNavController()

  onUiEvent(uiEvent, navController, editViewModel)

  NavHost(
    navController = navController,
    startDestination = Directions.category.screenRoute,
    modifier = Modifier.fillMaxSize()
  ) {
    composable(Directions.category.screenRoute) { EditCategoriesComposable() }
    composable(Directions.product.screenRoute) { EditProductsComposable() }
    composable(Directions.placeProduct.screenRoute) { EditPlaceProductsComposable() }
    composable(Directions.place.screenRoute) { EditPlacesComposable() }
  }
}

@Composable
private fun onUiEvent(
  uiEvent: UiEvent?,
  navController: NavController,
  editViewModel: EditViewModel
) {
  when (uiEvent) {
    is UiEvent.NavigateTo -> navigate(navController, uiEvent.directions)
    null -> {}
  }
  editViewModel.eventConsumed()
}

@Composable
private fun navigate(navController: NavController, directions: Directions) {
  navController.navigate(directions.screenRoute) {
    popUpTo(0)
  }
}