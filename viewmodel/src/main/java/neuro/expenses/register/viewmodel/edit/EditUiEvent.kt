package neuro.expenses.register.viewmodel.edit

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class EditUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun navigateToEditProduct() {
    _uiEvent.value = UiEvent.NavigateTo(Directions.product)
  }

  fun navigateToEditPlaceProduct() {
    _uiEvent.value = UiEvent.NavigateTo(Directions.placeProduct)
  }

  fun navigateToEditCategory() {
    _uiEvent.value = UiEvent.NavigateTo(Directions.category)
  }

  fun navigateToEditPlace() {
    _uiEvent.value = UiEvent.NavigateTo(Directions.place)
  }

  sealed class UiEvent(val directions: Directions) {
    class NavigateTo(directions: Directions) : UiEvent(directions)
  }

  enum class Directions(val screenRoute: String) {
    product("product"), placeProduct("placeProduct"), category("category"), place("place")
  }

}