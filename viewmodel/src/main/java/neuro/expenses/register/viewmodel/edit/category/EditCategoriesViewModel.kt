package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.viewmodel.fab.FloatingActionButtonViewModel
import neuro.expenses.register.viewmodel.mapper.toViewmodel
import neuro.expenses.register.viewmodel.model.CategoryModel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class EditCategoriesViewModel(
  private val observeCategoriesUseCase: ObserveCategoriesUseCase,
  override val editCategoryViewModel: EditCategoryViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState
) : ViewModel(), IEditCategoriesViewModel {
  val floatingActionButtonViewModel =
    FloatingActionButtonViewModel { onFloatingActionButtonClick() }

  override val modalBottomSheetVisible = mutableStateOf(false)
  override val categories = observeCategoriesUseCase.observeCategories().map { it.toViewmodel() }

  private val _uiEvent = EditCategoriesUiEvent()
  override val uiEvent = _uiEvent.uiEvent

  override fun onComposition() {
    enableFab()
  }

  override fun onCategoryClick(categoryModel: CategoryModel) {
    editCategoryViewModel.setEditCategoryViewModel(categoryModel, { onFinishEditAction() })
    _uiEvent.openEditCategory()
  }

  override fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  override fun onModalBottomSheetVisible() {
    scaffoldViewModelState.floatingActionButtonViewModel.value = null
    modalBottomSheetVisible.value = true
  }

  override fun onModalBottomSheetNotVisible() {
    enableFab()
    modalBottomSheetVisible.value = false
  }

  override fun onFloatingActionButtonClick() {
    editCategoryViewModel.reset()
    _uiEvent.openEditCategory()
  }

  private fun onFinishEditAction() {
    _uiEvent.closeEditPlaceProduct()
  }

  private fun enableFab() {
    scaffoldViewModelState.floatingActionButtonViewModel.value = floatingActionButtonViewModel
  }
}
