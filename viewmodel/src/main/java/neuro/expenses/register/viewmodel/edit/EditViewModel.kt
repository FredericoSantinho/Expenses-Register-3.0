package neuro.expenses.register.viewmodel.edit

import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.livedata.SingleLiveEvent

private val items = listOf(
  EditNavigationModel(Directions.product, UiEvent.NavigateToEditProduct),
  EditNavigationModel(Directions.placeProduct, UiEvent.NavigateToEditPlaceProduct),
  EditNavigationModel(Directions.category, UiEvent.NavigateToEditCategory),
  EditNavigationModel(Directions.place, UiEvent.NavigateToEditPlace)
)

class EditViewModel : ViewModel() {

  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  fun onOptionSelected(index: Int) {
    _uiEvent.value = items[index].uiEvent
  }

  fun getItems(): List<Directions> {
    return items.map { it.directions }
  }
}

sealed class UiEvent(val directions: Directions) {
  object NavigateToEditProduct : UiEvent(Directions.product)
  object NavigateToEditPlaceProduct : UiEvent(Directions.placeProduct)
  object NavigateToEditCategory : UiEvent(Directions.category)
  object NavigateToEditPlace : UiEvent(Directions.place)
}

enum class Directions {
  product, placeProduct, category, place
}
