package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModel(
  private val onProductCardClick: OnProductCardClick,
  productCardModel: ProductCardModel,
  val calendar: State<Calendar>
) : IProductCardViewModel {
  override val productId = mutableStateOf(productCardModel.id)
  override val description = mutableStateOf(productCardModel.description)
  override val category = mutableStateOf(productCardModel.category)
  override val price = mutableStateOf(productCardModel.price)
  override val iconUrl = mutableStateOf(productCardModel.iconUrl)
  val place = mutableStateOf(productCardModel.place)
  val amount = mutableStateOf(productCardModel.amount)

  override fun onCardClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardClick(productCardModel, calendar.value)
  }

  override fun onCardLongClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardLongClick(productCardModel, calendar.value)
  }

  private fun buildProductCardModel(): ProductCardModel {
    val productCardModel = ProductCardModel(
      productId.value,
      description.value,
      category.value,
      place.value,
      price.value,
      iconUrl.value,
      amount.value
    )
    return productCardModel
  }
}
