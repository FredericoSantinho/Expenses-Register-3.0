package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class BillsUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun openEditBill() {
    _uiEvent.value = UiEvent.OpenEditBill()
  }

  sealed class UiEvent {
    class OpenEditBill : UiEvent()
    class CloseEditBill : UiEvent()
  }
}