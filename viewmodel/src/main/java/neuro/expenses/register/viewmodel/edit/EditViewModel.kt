package neuro.expenses.register.viewmodel.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.common.asState

private val items = listOf(
  EditNavigationModel(Directions.product, UiEvent.NavigateToEditProduct),
  EditNavigationModel(Directions.placeProduct, UiEvent.NavigateToEditPlaceProduct),
  EditNavigationModel(Directions.category, UiEvent.NavigateToEditCategory),
  EditNavigationModel(Directions.place, UiEvent.NavigateToEditPlace)
)

class EditViewModel : ViewModel() {

  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun onOptionSelected(index: Int) {
    _uiEvent.value = items[index].uiEvent
  }

  fun getItems(): List<Directions> {
    return items.map { it.directions }
  }

  fun eventConsumed() {
    _uiEvent.value = null
  }
}

sealed class UiEvent(val directions: Directions) {
  object NavigateToEditProduct : UiEvent(Directions.product)
  object NavigateToEditPlaceProduct : UiEvent(Directions.placeProduct)
  object NavigateToEditCategory : UiEvent(Directions.category)
  object NavigateToEditPlace : UiEvent(Directions.place)
}

enum class Directions {
  product, placeProduct, category, place
}
