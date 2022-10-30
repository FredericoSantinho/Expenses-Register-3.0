package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.mutableStateOf

class ProductCardViewModel(
  description: String,
  category: String,
  price: String,
  iconUrl: String
) {
  val description = mutableStateOf(description)
  val category = mutableStateOf(category)
  val price = mutableStateOf(price)
  val iconUrl = mutableStateOf(iconUrl)

  fun onCardClick() {

  }
}