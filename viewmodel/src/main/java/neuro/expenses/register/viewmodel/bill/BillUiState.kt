package neuro.expenses.register.viewmodel.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class BillUiState() {
  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  val uiState = _uiState.asState()

  fun loading() {
    _uiState.value = UiState.Loading
  }

  fun billOpen() {
    _uiState.value = UiState.BillOpen
  }

  fun billClosed() {
    _uiState.value = UiState.BillClosed
  }

  sealed class UiState {
    object BillOpen : UiState()
    object BillClosed : UiState()
    object Loading : UiState()
  }
}