package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class EditCategoryUiState {
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()

  fun ready() {
    _uiState.value = UiState.Ready
  }

  fun deleteFailedActiveRelations() {
    _uiState.value = UiState.DeleteCategoryError
  }

  fun confirmCategoryDelete() {
    _uiState.value = UiState.ConfirmCategoryDelete
  }

  sealed class UiState {
    object Ready : UiState()
    object DeleteCategoryError : UiState()
    object ConfirmCategoryDelete : UiState()
  }
}