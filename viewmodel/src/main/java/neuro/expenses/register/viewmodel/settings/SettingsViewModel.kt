package neuro.expenses.register.viewmodel.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel() : ViewModel() {
  private val _uiState = MutableLiveData<UiState>(UiState.Ready)
  val uiState = _uiState

  fun onClearDatabase() {
    _uiState.value = UiState.ConfirmClearDatabase
  }

  fun onClearDatabaseConfirm() {
    _uiState.value = UiState.Ready
  }

  fun onClearDatabaseRefuse() {
    _uiState.value = UiState.Ready
  }

  sealed class UiState {
    object Ready : UiState()
    object ConfirmClearDatabase : UiState()
  }
}