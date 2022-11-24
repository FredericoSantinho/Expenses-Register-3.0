package neuro.expenses.register.viewmodel.edit.placeproduct

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class EditPlaceProductsUiState {
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()

  sealed class UiState {
    object Ready : UiState()
    object Editing : UiState()
  }

}