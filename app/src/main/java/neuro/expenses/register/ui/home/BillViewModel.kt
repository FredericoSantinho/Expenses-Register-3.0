package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BillViewModel : ViewModel() {
  val imageUrl = mutableStateOf("https://s3.minipreco.pt/medias/hc0/hf7/8915812384798.jpg")
  val place = mutableStateOf("Bitoque")
  val time = mutableStateOf("03h40")
  val date = mutableStateOf("20/10/2022")
  val total = mutableStateOf("19,80 €")
  val isBillOpen = mutableStateOf(true)
}