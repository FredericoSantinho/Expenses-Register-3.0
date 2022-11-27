package neuro.expenses.register.viewmodel.appbar

import neuro.expenses.register.viewmodel.appbar.AppBarUiEvent.UiEvent
import neuro.expenses.register.viewmodel.common.BaseUiEvent

class AppBarUiEvent : BaseUiEvent<UiEvent>() {
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