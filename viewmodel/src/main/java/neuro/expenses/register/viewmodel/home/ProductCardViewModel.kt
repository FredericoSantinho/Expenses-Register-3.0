package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class ProductCardViewModel(
  private val onProductCardClick: OnProductCardClick,
  productCardModel: ProductCardModel
) : IProductCardViewModel {
  override val productId = mutableStateOf(productCardModel.id)
  override val description = mutableStateOf(productCardModel.description)
  override val categoryModel =
    mutableStateOf(
      CategoryModel(
        productCardModel.categoryModel.id,
        productCardModel.categoryModel.name
      )
    )
  override val price = mutableStateOf(productCardModel.price)
  override val iconUrl = mutableStateOf(productCardModel.iconUrl)
  val place = mutableStateOf(productCardModel.place)
  val amount = mutableStateOf(productCardModel.amount)

  override fun onCardClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardClick(productCardModel)
  }

  override fun onCardLongClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardLongClick(productCardModel)
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
