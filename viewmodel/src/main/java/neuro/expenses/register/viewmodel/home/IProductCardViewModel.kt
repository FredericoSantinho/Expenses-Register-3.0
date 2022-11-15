package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState

interface IProductCardViewModel {
  val productId: MutableState<Long>
  val description: MutableState<String>
  val categoryName: MutableState<String>
  val categoryId: MutableState<Long>
  val price: MutableState<String>
  val iconUrl: MutableState<String>

  fun onCardClick()
  fun onCardLongClick()
}