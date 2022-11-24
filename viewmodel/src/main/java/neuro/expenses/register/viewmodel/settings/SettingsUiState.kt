package neuro.expenses.register.viewmodel.settings

import androidx.lifecycle.MutableLiveData

class SettingsUiState {
  private val _uiState = MutableLiveData<UiState>(UiState.Ready)
  val uiState = _uiState

  fun confirmClearDatabase() {
    _uiState.value = UiState.ConfirmClearDatabase
  }

  fun ready() {
    _uiState.value = UiState.Ready
  }

  sealed class UiState {
    object Ready : UiState()
    object ConfirmClearDatabase : UiState()
  }
}