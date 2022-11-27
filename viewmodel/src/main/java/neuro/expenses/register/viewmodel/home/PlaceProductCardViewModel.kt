package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class PlaceProductCardViewModel(
  private val onProductCardClick: OnProductCardClick,
  placeProductCardModel: PlaceProductCardModel
) : IProductCardViewModel {
  override val placeProductId = mutableStateOf(placeProductCardModel.id)
  override val description = mutableStateOf(placeProductCardModel.description)
  override val categoryModel =
    mutableStateOf(
      CategoryModel(
        placeProductCardModel.categoryModel.id,
        placeProductCardModel.categoryModel.name,
        ""
      )
    )
  override val price = mutableStateOf(placeProductCardModel.price)
  override val iconUrl = mutableStateOf(placeProductCardModel.iconUrl)
  val place = mutableStateOf(placeProductCardModel.place)
  val amount = mutableStateOf(placeProductCardModel.amount)

  override fun onCardClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardClick(productCardModel)
  }

  override fun onCardLongClick() {
    val productCardModel = buildProductCardModel()
    onProductCardClick.onProductCardLongClick(productCardModel)
  }

  private fun buildProductCardModel(): PlaceProductCardModel {
    val placeProductCardModel = PlaceProductCardModel(
      placeProductId.value,
      description.value,
      categoryModel.value,
      place.value,
      price.value,
      iconUrl.value,
      amount.value
    )
    return placeProductCardModel
  }
}
