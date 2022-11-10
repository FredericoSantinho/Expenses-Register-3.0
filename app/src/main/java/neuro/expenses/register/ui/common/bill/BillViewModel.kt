package neuro.expenses.register.ui.common.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.common.viewmodel.asState
import neuro.expenses.register.ui.common.bill.model.BillModel

private val EMPTY = BillModel(0L, "", "", "", "", "", false)

class BillViewModel(editable: Boolean = false, billModel: BillModel = EMPTY) {
  val id = mutableStateOf(billModel.id)
  val iconUrl = mutableStateOf(billModel.iconUrl)
  val place = mutableStateOf(billModel.place)
  val time = mutableStateOf(billModel.time)
  val date = mutableStateOf(billModel.date)
  val total = mutableStateOf(billModel.total)

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  val uiState = _uiState.asState()

  init {
    if (editable) {
      setEditableBillState()
    }
  }

  fun setBillModel(billModel: BillModel) {
    id.value = billModel.id
    iconUrl.value = billModel.iconUrl
    place.value = billModel.place
    time.value = billModel.time
    date.value = billModel.date
    total.value = billModel.total
    if (billModel.isOpen) {
      setOpenedBillState()
    } else {
      setClosedBillState()
    }
  }

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
