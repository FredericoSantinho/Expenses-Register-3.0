package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.viewmodel.bills.BillsUiEvent.UiEvent
import neuro.expenses.register.viewmodel.common.BaseUiEvent

class BillsUiEvent : BaseUiEvent<UiEvent>() {
  fun openBillDetailed() {
    _uiEvent.value = UiEvent.OpenBillDetailed()
  }

  sealed class UiEvent {
    class OpenBillDetailed : UiEvent()
    class CloseBillDetailed : UiEvent()
  }
}