package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.fab.FloatingActionButtonViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class EditCategoriesViewModel(
  val editCategoryViewModel: EditCategoryViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState
) : ViewModel() {
  val floatingActionButtonViewModel =
    FloatingActionButtonViewModel { onFloatingActionButtonClick() }

  private val _uiEvent = EditCategoriesUiEvent()
  val uiEvent = _uiEvent.uiEvent

  fun onComposition() {
    enableFab()
  }

  fun onCategoryClick(categoryModel: CategoryModel) {
    editCategoryViewModel.setEditCategoryViewModel(categoryModel)
    _uiEvent.openEditCategory()
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  fun onModalBottomSheetVisible() {
    scaffoldViewModelState.floatingActionButtonViewModel.value = null
  }

  fun onModalBottomSheetNotVisible() {
    enableFab()
  }

  private fun onFloatingActionButtonClick() {
    editCategoryViewModel.reset()
    _uiEvent.openEditCategory()
  }

  private fun enableFab() {
    scaffoldViewModelState.floatingActionButtonViewModel.value = floatingActionButtonViewModel
  }

  val categories = mutableStateOf(
    listOf(
      CategoryModel(1, "Portagens", ""),
      CategoryModel(2, "Borga", ""),
      CategoryModel(
        3,
        "Café",
        "https://toppng.com/uploads/preview/coffee-png-11552943089jgfzxcloo8.png"
      )
    )
  )
}
