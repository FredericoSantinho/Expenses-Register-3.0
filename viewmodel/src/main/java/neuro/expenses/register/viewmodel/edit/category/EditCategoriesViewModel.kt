package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoriesViewModel() : ViewModel() {
  fun onCategoryClick(categoryModel: CategoryModel) {

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