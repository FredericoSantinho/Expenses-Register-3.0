package neuro.expenses.register.viewmodel.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.main.MainViewModel

private val items = listOf(
  EditNavigationModel(UiEvent.NavigateTo(Directions.product)),
  EditNavigationModel(UiEvent.NavigateTo(Directions.placeProduct)),
  EditNavigationModel(UiEvent.NavigateTo(Directions.category)),
  EditNavigationModel(UiEvent.NavigateTo(Directions.place))
)

class EditViewModel(private val mainViewModel: MainViewModel) : ViewModel() {
  private val appBarViewModel = AppBarViewModel()

  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun onOptionSelected(index: Int) {
    _uiEvent.value = items[index].uiEvent
  }

  fun getItems(): List<Directions> {
    return items.map { it.uiEvent.directions }
  }

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun onComposition() {
    mainViewModel.appBarViewModelState.value = appBarViewModel
  }
}

sealed class UiEvent(val directions: Directions) {
  class NavigateTo(directions: Directions) : UiEvent(directions)
}

enum class Directions {
  product, placeProduct, category, place
}
