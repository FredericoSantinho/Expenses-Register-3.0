package neuro.expenses.register.viewmodel.edit.bill

import androidx.compose.runtime.mutableStateOf
import java.util.*

class EditBillViewModel() {

  val placeName = mutableStateOf("")
  val calendar = mutableStateOf(Calendar.getInstance())

  fun onSaveButton() {
  }

  fun onDeleteButton() {
  }
}
