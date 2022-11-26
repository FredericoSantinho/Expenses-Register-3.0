package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.usecase.category.DeleteCategoryError
import neuro.expenses.register.domain.usecase.category.DeleteCategoryUseCase
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoryViewModel(
  private val deleteCategoryUseCase: DeleteCategoryUseCase,
  private val schedulerProvider: SchedulerProvider
) : IEditCategoryViewModel {
  override val id = mutableStateOf(-1L)
  override val name = mutableStateOf("")
  override val iconUrl = mutableStateOf("")
  val onFinishEditAction = mutableStateOf({ })

  private val _uiEvent = EditCategoryUiEvent()
  override val uiEvent = _uiEvent.uiEvent
  private val _uiState = EditCategoryUiState()
  override val uiState = _uiState.uiState

  override fun setEditCategoryViewModel(
    categoryModel: CategoryModel,
    onFinishEditAction: () -> Unit
  ) {
    id.value = categoryModel.id
    name.value = categoryModel.name
    iconUrl.value = categoryModel.iconUrl
    this.onFinishEditAction.value = onFinishEditAction
  }

  override fun reset() {
    id.value = -1L
    name.value = ""
    iconUrl.value = ""
    onFinishEditAction.value = {}
  }

  override fun onNameChange() {

  }

  override fun onSaveButton() {

  }

  override fun onDeleteButton() {
    deleteCategoryUseCase.deleteCategory(id.value).subscribeOn(schedulerProvider.io()).subscribe({
      onFinishEditAction.value()
    }, {
      if (it is DeleteCategoryError) {
        _uiState.deleteFailedActiveRelations()
      } else {
        throw it
      }
    })
  }

  override fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  override fun onDeleteCategoryErrorDialogDismiss() {
    _uiState.ready()
  }
}