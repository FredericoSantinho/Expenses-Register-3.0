package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.asLiveData
import neuro.expenses.register.common.view.model.asState
import neuro.expenses.register.ui.home.model.ProductView

class ProductsListViewModel : ViewModel() {
  val products = mutableStateOf(getProdutsList())

  private fun getProdutsList(): List<ProductView> {
    val list: MutableList<ProductView> = mutableListOf()
    var s = "https://www.computerhope.com/jargon/b/black.jpg"
    for (i in 1..5) {
      list.add(ProductView("Sagres Média 0,33cl " + i, "Borga", "1,30 €", s))
    }
    return list
  }

  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  sealed class UiEvent

  sealed class UiState {
    object Ready : UiState()
  }

  sealed class UiStateError
}