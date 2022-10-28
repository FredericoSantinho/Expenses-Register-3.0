package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.asLiveData
import neuro.expenses.register.common.view.model.asState

class HomeViewModel : ViewModel() {
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  sealed class UiEvent {
    class ShowRegisterSuccess(val productName: String) : UiEvent()
  }

  sealed class UiState {
    object Ready : UiState()
  }
}