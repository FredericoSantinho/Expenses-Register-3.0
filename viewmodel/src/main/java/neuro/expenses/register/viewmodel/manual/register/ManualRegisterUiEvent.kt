package neuro.expenses.register.viewmodel.manual.register

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiEvent.UiEvent

class ManualRegisterUiEvent : BaseUiEvent<UiEvent>() {
  fun showRegisterSuccess(description: String) {
    _uiEvent.value = UiEvent.ShowRegisterSuccess(description)
  }

  sealed class UiEvent {
    data class ShowRegisterSuccess(val productDescription: String) : UiEvent()
  }
}