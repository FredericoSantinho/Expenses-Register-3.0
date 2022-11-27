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
    _uiState.value = UiState.DeleteCategoryErrorActiveRelations
  }

  fun confirmCategoryDelete() {
    _uiState.value = UiState.ConfirmCategoryDelete
  }

  fun createFailedNameConflict() {
    _uiState.value = UiState.CreateCategoryErrorNameConflict
  }

  fun updateFailedNameConflict() {
    _uiState.value = UiState.UpdateCategoryErrorNameConflict
  }

  sealed class UiState {
    object Ready : UiState()
    object DeleteCategoryErrorActiveRelations : UiState()
    object ConfirmCategoryDelete : UiState()
    object CreateCategoryErrorNameConflict : UiState()
    object UpdateCategoryErrorNameConflict : UiState()
  }
}