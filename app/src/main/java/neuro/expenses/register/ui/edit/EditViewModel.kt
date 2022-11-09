package neuro.expenses.register.ui.edit

import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.viewmodel.asLiveData

class EditViewModel : ViewModel() {

  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  fun onOptionSelected(index: Int) {
    when (index) {
      0 -> _uiEvent.value = UiEvent.NavigateToEditProduct
      1 -> _uiEvent.value = UiEvent.NavigateToEditCategory
      2 -> _uiEvent.value = UiEvent.NavigateToEditPlace
    }
  }

  fun getItems(): List<String> {
    return listOf("Product", "Category", "Place")
  }
}

sealed class UiEvent(val directions: Directions) {
  object NavigateToEditProduct : UiEvent(Directions.product)
  object NavigateToEditCategory : UiEvent(Directions.category)
  object NavigateToEditPlace : UiEvent(Directions.place)
}

enum class Directions {
  product,
  category,
  place
}