package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class ManualRegisterUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun showRegisterSuccess(description: String) {
    _uiEvent.value = UiEvent.ShowRegisterSuccess(description)
  }

  sealed class UiEvent {
    class ShowRegisterSuccess(val productDescription: String) : UiEvent()
  }
}