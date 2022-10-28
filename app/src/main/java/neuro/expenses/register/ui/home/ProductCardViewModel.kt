package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProductCardViewModel : ViewModel() {
  val description = mutableStateOf("Tosta de Atúm")
  val category = mutableStateOf("Restau")
  val price = mutableStateOf("3,50 €")
  val imageUrl = mutableStateOf("https://s3.minipreco.pt/medias/hc0/hf7/8915812384798.jpg")

  fun onCardClick() {

  }
}
