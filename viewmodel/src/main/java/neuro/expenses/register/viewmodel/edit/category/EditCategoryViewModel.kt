package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.CreateCategoryError
import neuro.expenses.register.domain.repository.category.UpdateCategoryError
import neuro.expenses.register.domain.usecase.category.CreateCategoryUseCase
import neuro.expenses.register.domain.usecase.category.DeleteCategoryError
import neuro.expenses.register.domain.usecase.category.DeleteCategoryUseCase
import neuro.expenses.register.domain.usecase.category.UpdateCategoryUseCase
import neuro.expenses.register.viewmodel.common.BaseViewModelModule
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoryViewModel(
  private val createCategoryUseCase: CreateCategoryUseCase,
  private val updateCategoryUseCase: UpdateCategoryUseCase,
  private val deleteCategoryUseCase: DeleteCategoryUseCase,
  schedulerProvider: SchedulerProvider
) : BaseViewModelModule(schedulerProvider), IEditCategoryViewModel {
  override val id = mutableStateOf(-1L)
  override var currentName = ""
  override var name = mutableStateOf("")
  override val iconUrl = mutableStateOf("")
  val onFinishEditAction = mutableStateOf({ })

  private val _uiEvent = EditCategoryUiEvent()
  override val uiEvent = _uiEvent.uiEvent
  private val _uiState = EditCategoryUiState()
  override val uiState = _uiState.uiState

  override fun setEditCategoryViewModel(
    categoryModel: CategoryModel, onFinishEditAction: () -> Unit
  ) {
    id.value = categoryModel.id
    currentName = categoryModel.name
    name.value = categoryModel.name
    iconUrl.value = categoryModel.iconUrl
    this.onFinishEditAction.value = onFinishEditAction
  }

  override fun reset() {
    id.value = -1L
    currentName = ""
    name.value = ""
    iconUrl.value = ""
    onFinishEditAction.value = {}
  }

  override fun onNameChange() {

  }

  override fun onSaveButton() {
    if (id.value == -1L) {
      createCategoryUseCase.createCategory(name.value, iconUrl.value)
        .baseSubscribe(onComplete = { onFinishEditAction.value() }, onError = {
          if (it is CreateCategoryError) {
            _uiState.createFailedNameConflict()
          } else {
            throw it
          }
        })
    } else {
      updateCategoryUseCase.updateCategory(buildCategoryDto())
        .baseSubscribe(onComplete = { onFinishEditAction.value() }, onError = {
          if (it is UpdateCategoryError) {
            _uiState.updateFailedNameConflict()
          } else {
            throw it
          }
        })
    }
  }

  override fun onDeleteButton() {
    _uiState.confirmCategoryDelete()
  }

  override fun onConfirmDelete() {
    deleteCategoryUseCase.deleteCategory(id.value)
      .baseSubscribe(onComplete = { onFinishEditAction.value() }, onError = {
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

  override fun onCreateCategoryErrorDialogDismiss() {
    _uiState.ready()
  }

  override fun onUpdateCategoryErrorDialogDismiss() {
    _uiState.ready()
  }

  override fun onDeleteCategoryErrorDialogDismiss() {
    _uiState.ready()
  }

  override fun onConfirmDeleteDismiss() {
    _uiState.ready()
  }

  private fun buildCategoryDto(): CategoryDto {
    return CategoryDto(id.value, name.value, iconUrl.value)
  }
}