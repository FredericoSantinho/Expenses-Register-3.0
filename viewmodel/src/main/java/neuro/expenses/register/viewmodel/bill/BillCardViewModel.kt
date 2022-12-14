package neuro.expenses.register.viewmodel.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.bill.model.BillModel
import java.util.*

private val EMPTY = BillModel(0L, "", "", "", "", "", false, Calendar.getInstance())

class BillCardViewModel(
  opened: Boolean = false,
  billModel: BillModel = EMPTY,
  private val onBillClick: (Long) -> Unit = {}
) : IBillCardViewModel {
  override val id = mutableStateOf(billModel.id)
  override val iconUrl = mutableStateOf(billModel.iconUrl)
  override val place = mutableStateOf(billModel.place)
  override val time = mutableStateOf(billModel.time)
  override val date = mutableStateOf(billModel.date)
  override val total = mutableStateOf(billModel.total)

  private val _uiState = BillUiState()
  override val uiState = _uiState.uiState

  init {
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
    onBillClick(id.value)
  }

  override fun onCardLongClick() {
  }

  override fun onEditClick() {

  }

  fun setLoadingState() {
    _uiState.loading()
  }

  fun setOpenedBillState() {
    _uiState.billOpen()
  }

  fun setClosedBillState() {
    _uiState.billClosed()
  }
}
