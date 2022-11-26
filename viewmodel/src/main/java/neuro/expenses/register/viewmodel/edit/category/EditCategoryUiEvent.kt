package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class EditCategoryUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  sealed class UiEvent
}