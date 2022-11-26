package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoryViewModel {
  val name = mutableStateOf("")
  val iconUrl = mutableStateOf("")

  fun setEditCategoryViewModel(categoryModel: CategoryModel) {
    name.value = categoryModel.name
    iconUrl.value = categoryModel.iconUrl
  }

  fun reset() {
    name.value = ""
    iconUrl.value = ""
  }

  fun onNameChange() {

  }
}