package neuro.expenses.register.viewmodel.edit

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent

class EditUiEvent : BaseUiEvent<UiEvent>() {
  fun navigateToEditCategory() {
    _uiEvent.value = UiEvent.NavigateTo(0)
  }

  sealed class UiEvent(val index: Int) {
    class NavigateTo(index: Int) : UiEvent(index)
  }

  enum class Destination() {
    categories()
  }

}