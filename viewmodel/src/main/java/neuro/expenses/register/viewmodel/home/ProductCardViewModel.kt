package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.model.CategoryModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModel(
  private val onProductCardClick: OnProductCardClick,
  productCardModel: ProductCardModel,
  val calendar: State<Calendar>
) : IProductCardViewModel {
  override val productId = mutableStateOf(productCardModel.id)
  override val description = mutableStateOf(productCardModel.description)
  override val categoryModel =
    mutableStateOf(
      CategoryModel(
        productCardModel.categoryModel.id.value,
        productCardModel.categoryModel.name.value
      )
    )
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
      categoryModel.value,
      place.value,
      price.value,
      iconUrl.value,
      amount.value
    )
    return productCardModel
  }
}
