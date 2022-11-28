package neuro.expenses.register.viewmodel.edit

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent

class EditUiEvent : BaseUiEvent<UiEvent>() {
  fun navigateToEditCategory() {
    _uiEvent.value = UiEvent.NavigateTo(0, Destination.categories)
  }

  fun navigateToEditProduct() {
    _uiEvent.value = UiEvent.NavigateTo(1, Destination.products)
  }

  fun navigateToEditPlaceProduct() {
    _uiEvent.value = UiEvent.NavigateTo(2, Destination.placeProducts)
  }

  fun navigateToEditPlace() {
    _uiEvent.value = UiEvent.NavigateTo(3, Destination.places)
  }

  sealed class UiEvent(val index: Int, val destination: Destination) {
    class NavigateTo(index: Int, destination: Destination) : UiEvent(index, destination)
  }

  enum class Destination(val screenRoute: String) {
    categories("category"), products("product"), placeProducts("placeProduct"), places("place")
  }

}