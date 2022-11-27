package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.model.CategoryModel

interface IEditCategoryViewModel {
  val id: MutableState<Long>
  val name: MutableState<String>
  val iconUrl: MutableState<String>

  val uiEvent: State<EditCategoryUiEvent.UiEvent?>
  val uiState: State<EditCategoryUiState.UiState>

  fun setEditCategoryViewModel(categoryModel: CategoryModel, onFinishEditAction: () -> Unit)
  fun reset()
  fun onNameChange()
  fun onSaveButton()
  fun onDeleteButton()
  fun onConfirmDelete()
  fun eventConsumed()
  fun onDeleteCategoryErrorDialogDismiss()
  fun onConfirmDeleteDismiss()
}