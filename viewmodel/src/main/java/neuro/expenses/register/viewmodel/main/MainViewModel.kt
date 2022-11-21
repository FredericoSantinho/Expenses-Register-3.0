package neuro.expenses.register.viewmodel.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.UiEvent
import neuro.expenses.register.viewmodel.common.asState

class MainViewModel : ViewModel() {
  val appBarViewModelState = mutableStateOf(AppBarViewModel())

  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun onConfigButton() {
    _uiEvent.value = UiEvent.NavigateToSettings
  }

  sealed class UiEvent {
    object NavigateToSettings : UiEvent()
  }
}