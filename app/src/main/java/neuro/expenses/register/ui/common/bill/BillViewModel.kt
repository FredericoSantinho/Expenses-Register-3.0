package neuro.expenses.register.ui.common.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.common.viewmodel.asState

class BillViewModel {
  // TODO: create id
  val id = mutableStateOf(0L)
  val iconUrl = mutableStateOf("")
  val place = mutableStateOf("")
  val time = mutableStateOf("")
  val date = mutableStateOf("")
  val total = mutableStateOf("")

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  val uiState = _uiState.asState()

  fun onClick() {

  }

  fun onLongClick() {

  }

  fun onEditClick() {

  }


  fun setLoadingState() {
    _uiState.value = UiState.Loading
  }

  fun setOpenedBillState() {
    _uiState.value = UiState.BillOpen
  }

  fun setClosedBillState() {
    _uiState.value = UiState.BillClosed
  }

  fun setEditableBillState() {
    _uiState.value = UiState.BillEditable
  }
}

sealed class UiState {
  object BillOpen : UiState()
  object BillClosed : UiState()
  object BillEditable : UiState()
  object Loading : UiState()
}
