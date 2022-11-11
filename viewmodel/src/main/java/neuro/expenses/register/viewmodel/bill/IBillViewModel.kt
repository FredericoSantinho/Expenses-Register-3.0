package neuro.expenses.register.viewmodel.bill

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.bill.model.BillModel

interface IBillViewModel {
  val id: MutableState<Long>
  val iconUrl: MutableState<String>
  val place: MutableState<String>
  val time: MutableState<String>
  val date: MutableState<String>
  val total: MutableState<String>

  val uiState: State<UiState>

  fun setBillModel(billModel: BillModel)
  fun onCardClick()
  fun onCardLongClick()
  fun onEditClick()
}
