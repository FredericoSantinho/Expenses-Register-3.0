package neuro.expenses.register.ui.home

import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.dto.ProductDto

class HomeViewModel : ViewModel() {
  fun getPlaceProducts(): List<ProductDto> {
    val list = mutableListOf<ProductDto>()
    for (n in 1..5) {
      list.add(
        ProductDto(
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