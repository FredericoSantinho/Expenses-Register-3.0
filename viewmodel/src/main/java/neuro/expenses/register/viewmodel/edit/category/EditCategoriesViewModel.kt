package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoriesViewModel(val editCategoryViewModel: EditCategoryViewModel) : ViewModel() {
  private val _uiEvent = EditCategoriesUiEvent()
  val uiEvent = _uiEvent.uiEvent

  fun onCategoryClick(categoryModel: CategoryModel) {
    editCategoryViewModel.setEditCategoryViewModel(categoryModel)
    _uiEvent.openEditCategory()
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  val categories = mutableStateOf(
    listOf(
      CategoryModel(1, ".Portágens", ""),
      CategoryModel(2, "Borga", ""),
      CategoryModel(
        3,
        "Café",
        "https://toppng.com/uploads/preview/coffee-png-11552943089jgfzxcloo8.png"
      )
    )
  )
}