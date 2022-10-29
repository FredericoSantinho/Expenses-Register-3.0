package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf

class BillViewModel(i: Int = 0, isEdit: Boolean = false) {
  // TODO: create id
  val id = mutableStateOf(hashCode())
  val iconUrl = mutableStateOf("https://s3.minipreco.pt/medias/hc0/hf7/8915812384798.jpg")
  val place = mutableStateOf("Bitoque " + i)
  val time = mutableStateOf("03h40")
  val date = mutableStateOf("20/10/2022")
  val total = mutableStateOf("19,80 €")
  val isBillOpen = mutableStateOf(true)
  val isEdit = mutableStateOf(isEdit)

  fun onClick() {

  }

  fun onLongClick() {

  }

  fun onEditClick() {

  }
}