package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.mutableStateOf

class BillViewModel(isEdit: Boolean = false) {
  // TODO: create id
  val id = mutableStateOf(hashCode())
  val iconUrl = mutableStateOf("")
  val place = mutableStateOf("N/A")
  val time = mutableStateOf("N/A")
  val date = mutableStateOf("N/A")
  val total = mutableStateOf("0,00 €")
  val isBillOpen = mutableStateOf(false)
  val isEdit = mutableStateOf(isEdit)

  fun onClick() {

  }

  fun onLongClick() {

  }

  fun onEditClick() {

  }
}