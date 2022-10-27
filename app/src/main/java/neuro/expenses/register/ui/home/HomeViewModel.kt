package neuro.expenses.register.ui.home

import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.entity.Product

class HomeViewModel : ViewModel() {
  fun getPlaceProducts(): List<Product> {
    val list = mutableListOf<Product>()
    for (n in 1..5) {
      list.add(
        Product(
          "Bolacha Oreo Coberta Chocolate Branco 246g",
          "Alimentação",
          2.09,
          "https://s3.minipreco.pt/medias/hc0/hf7/8915812384798.jpg"
        )
      )
    }
    return list
  }

  fun getPlacesNames(): List<String> {
    return listOf("Bitoque")
  }
}