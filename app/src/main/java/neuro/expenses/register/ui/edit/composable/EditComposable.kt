package neuro.expenses.register.ui.edit.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.ui.composables.dropdown.OnItemSelect
import neuro.expenses.register.ui.edit.Directions
import neuro.expenses.register.ui.edit.EditViewModel
import neuro.expenses.register.ui.edit.UiEvent
import neuro.expenses.register.ui.edit.category.composable.EditCategoryComposable
import neuro.expenses.register.ui.edit.place.composable.EditPlaceComposable
import neuro.expenses.register.ui.edit.product.composable.EditProductComposable
import org.koin.androidx.compose.getViewModel

@Composable
fun EditComposable(editViewModel: EditViewModel = getViewModel()) {
  val uiEvent by editViewModel.uiEvent.observeAsState(null)

  val navController = rememberNavController()

  onUiEvent(uiEvent, navController)

  Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end = 4.dp)) {
    DropdownButton(editViewModel.getItems(), object : OnItemSelect {
      override fun onItemSelect(index: Int) {
        editViewModel.onOptionSelected(index)
      }
    })
    NavHost(
      navController = navController,
      startDestination = Directions.product.toString(),
      modifier = Modifier.fillMaxSize()
    ) {
      composable(Directions.product.toString()) { EditProductComposable() }
      composable(Directions.category.toString()) { EditCategoryComposable() }
      composable(Directions.place.toString()) { EditPlaceComposable() }
    }
  }
}

@Composable
private fun onUiEvent(uiEvent: UiEvent?, navController: NavController) {
  when (uiEvent) {
    UiEvent.NavigateToEditProduct -> navigate(navController, Directions.product)
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