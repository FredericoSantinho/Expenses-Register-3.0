package neuro.expenses.register.viewmodel.edit.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemModel
import java.util.*

class EditBillViewModel() {

  val placeName = mutableStateOf("")
  val calendar = mutableStateOf(Calendar.getInstance())
  val billItems = mutableStateOf(emptyList<BillItemModel>())
  val total = mutableStateOf("")

  fun onSaveButton() {
  }

  fun onDeleteButton() {
  }
}
