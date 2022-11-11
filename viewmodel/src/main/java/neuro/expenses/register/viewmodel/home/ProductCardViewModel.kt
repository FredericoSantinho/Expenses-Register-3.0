package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModel(
  private val homeViewModel: IHomeViewModel,
  productCardModel: ProductCardModel,
  val calendar: State<Calendar>
) {
  val description = mutableStateOf(productCardModel.description)
  val category = mutableStateOf(productCardModel.category)
  val place = mutableStateOf(productCardModel.place)
  val price = mutableStateOf(productCardModel.price)
  val amount = mutableStateOf(productCardModel.amount)
  val iconUrl = mutableStateOf(productCardModel.iconUrl)

  fun onCardClick() {
    val productCardModel = ProductCardModel(
      description.value,
      category.value,
      place.value,
      price.value,
      amount.value,
      iconUrl.value
    )
    homeViewModel.onProductCardClick(productCardModel, calendar.value)
  }
}
