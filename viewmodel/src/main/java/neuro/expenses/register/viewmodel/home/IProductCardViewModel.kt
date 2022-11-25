package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState
import neuro.expenses.register.viewmodel.model.CategoryModel

interface IProductCardViewModel {
  val placeProductId: MutableState<Long>
  val description: MutableState<String>
  val categoryModel: MutableState<CategoryModel>
  val price: MutableState<String>
  val iconUrl: MutableState<String>

  fun onCardClick()
  fun onCardLongClick()
}