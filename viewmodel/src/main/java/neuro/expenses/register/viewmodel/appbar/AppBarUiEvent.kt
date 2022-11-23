package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class AppBarUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun navigateToSettings() {
    _uiEvent.value = UiEvent.NavigateToSettings()
  }

  fun focusSearch() {
    _uiEvent.value = UiEvent.FocusSearch()
  }

  sealed class UiEvent {
    class NavigateToSettings : UiEvent()
    class FocusSearch : UiEvent()
  }
}