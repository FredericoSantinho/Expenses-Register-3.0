package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.asLiveData
import neuro.expenses.register.common.view.model.asState

class ProductsListViewModel : ViewModel() {
  val products = mutableStateListOf<ProductCardViewModel>()

  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  init {
    products.addAll(getProdutsListViewModels())
  }

  private fun getProdutsListViewModels(): List<ProductCardViewModel> {
    val list: MutableList<ProductCardViewModel> = mutableListOf()
    var s = "https://s3.minipreco.pt/medias/hc0/hf7/8915812384798.jpg"
    for (i in 1..5) {
      val description = "Tosta de Atúm " + i
      val category = "Restau"
      val price = "3,50 €"
      list.add(ProductCardViewModel(description, category, price, s))
    }
    return list
  }

  sealed class UiEvent

  sealed class UiState {
    object Ready : UiState()
  }

  sealed class UiStateError
}