package neuro.expenses.register.viewmodel.edit

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent

class EditUiEvent : BaseUiEvent<UiEvent>() {
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