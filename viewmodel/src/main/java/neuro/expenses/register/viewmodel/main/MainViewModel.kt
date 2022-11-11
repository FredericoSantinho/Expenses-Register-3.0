package neuro.expenses.register.viewmodel.main

import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.livedata.SingleLiveEvent

class MainViewModel : ViewModel() {
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  fun onConfigButton() {
    _uiEvent.value = UiEvent.NavigateToSettings
  }

  sealed class UiEvent {
    object NavigateToSettings : UiEvent()
  }
}