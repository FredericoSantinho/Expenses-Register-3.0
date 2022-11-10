package neuro.expenses.register.ui.common.bill

import androidx.compose.runtime.mutableStateOf

class BillViewModel(isEdit: Boolean = false) {
  // TODO: create id
  val id = mutableStateOf(hashCode())
  val iconUrl = mutableStateOf("")
  val place = mutableStateOf("")
  val time = mutableStateOf("")
  val date = mutableStateOf("")
  val total = mutableStateOf("")
  val isBillOpen = mutableStateOf(false)
  val isEdit = mutableStateOf(isEdit)
  val isLoading = mutableStateOf(true)

  fun onClick() {

  }

  fun onLongClick() {

  }

  fun onEditClick() {

  }
}