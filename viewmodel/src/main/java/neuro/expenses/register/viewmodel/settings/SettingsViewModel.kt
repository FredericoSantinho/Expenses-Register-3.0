package neuro.expenses.register.viewmodel.settings

import androidx.lifecycle.ViewModel

class SettingsViewModel() : ViewModel() {

  private val _uiState = SettingsUiState()
  val uiState = _uiState.uiState

  fun onClearDatabase() {
    _uiState.confirmClearDatabase()
  }

  fun onClearDatabaseConfirm() {
    _uiState.ready()
  }

  fun onClearDatabaseRefuse() {
    _uiState.ready()
  }
}