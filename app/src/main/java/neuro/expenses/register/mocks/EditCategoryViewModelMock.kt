package neuro.expenses.register.mocks

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.edit.category.EditCategoryUiEvent
import neuro.expenses.register.viewmodel.edit.category.EditCategoryUiState
import neuro.expenses.register.viewmodel.edit.category.IEditCategoryViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoryViewModelMock() : IEditCategoryViewModel {
  override val id = mutableStateOf(-1L)
  override val name = mutableStateOf("")
  override val iconUrl = mutableStateOf("")

  override val uiEvent = mutableStateOf<EditCategoryUiEvent.UiEvent?>(null)
  override val uiState =
    mutableStateOf<EditCategoryUiState.UiState>(EditCategoryUiState.UiState.Ready)

  override fun setEditCategoryViewModel(
    categoryModel: CategoryModel, onFinishEditAction: () -> Unit
  ) {
  }

  override fun reset() {}
  override fun onNameChange() {}
  override fun onSaveButton() {}
  override fun onDeleteButton() {}
  override fun onConfirmDelete() {}
  override fun eventConsumed() {}
  override fun onDeleteCategoryErrorDialogDismiss() {}
  override fun onConfirmDeleteDismiss() {}
}