package neuro.expenses.register.viewmodel.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.bill.model.BillModel
import neuro.expenses.register.viewmodel.common.asState
import java.util.*

private val EMPTY = BillModel(0L, "", "", "", "", "", false, Calendar.getInstance())

class BillViewModel(
  editable: Boolean = false,
  opened: Boolean = false,
  billModel: BillModel = EMPTY,
  private val onLongClick: (BillModel) -> Unit = {}
) : IBillViewModel {
  override val billModel = mutableStateOf(billModel)
  override val id = mutableStateOf(billModel.id)
  override val iconUrl = mutableStateOf(billModel.iconUrl)
  override val place = mutableStateOf(billModel.place)
  override val time = mutableStateOf(billModel.time)
  override val date = mutableStateOf(billModel.date)
  override val total = mutableStateOf(billModel.total)

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  override val uiState = _uiState.asState()

  init {
    if (editable) {
      setEditableBillState()
    }
    if (opened) {
      setOpenedBillState()
    } else {
      setClosedBillState()
    }
  }

  override fun setBillModel(billModel: BillModel) {
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

  override fun onCardClick() {

  }

  override fun onCardLongClick() {
    onLongClick(billModel.value)
  }

  override fun onEditClick() {

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
