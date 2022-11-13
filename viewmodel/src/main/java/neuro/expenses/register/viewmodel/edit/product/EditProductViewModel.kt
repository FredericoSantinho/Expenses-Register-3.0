package neuro.expenses.register.viewmodel.edit.product

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase

class EditProductViewModel(observeCategoriesUseCase: ObserveCategoriesUseCase) {
  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val price = mutableStateOf("")
  val iconUrl = mutableStateOf("")

  val categories = observeCategoriesUseCase.observeCategories()

  fun onDescriptionChange() {

  }

  fun onCategoryChange() {

  }

  fun onSaveButton() {

  }
}