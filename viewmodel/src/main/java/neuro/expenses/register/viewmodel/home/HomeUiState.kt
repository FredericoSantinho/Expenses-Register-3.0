package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class HomeUiState {
  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  val uiState = _uiState.asState()

  fun ready() {
    _uiState.value = UiState.Ready
  }

  fun showLocationPermissionDialog() {
    _uiState.value = UiState.ShowLocationPermissionDialog
  }

  sealed class UiState {
    object Loading : UiState()
    object Ready : UiState()
    object ShowLocationPermissionDialog : UiState()
  }
}