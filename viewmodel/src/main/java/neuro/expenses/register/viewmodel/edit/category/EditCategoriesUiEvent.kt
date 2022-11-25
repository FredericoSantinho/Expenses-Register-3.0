package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class EditCategoriesUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun openEditCategory() {
    _uiEvent.value = UiEvent.OpenEditCategory()
  }

  sealed class UiEvent {
    class OpenEditCategory : UiEvent()
    class CloseEditCategory : UiEvent()
  }
}
