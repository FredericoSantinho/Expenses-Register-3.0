package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState

interface IProductCardViewModel {
  val productId: MutableState<Long>
  val description: MutableState<String>
  val category: MutableState<String>
  val price: MutableState<String>
  val iconUrl: MutableState<String>

  fun onCardClick()
  fun onCardLongClick()
}