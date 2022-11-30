package neuro.expenses.register.presentation.mocks.category

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesUiEvent
import neuro.expenses.register.viewmodel.edit.category.IEditCategoriesViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel

class EditCategoriesViewModelMock() : IEditCategoriesViewModel {

  override val modalBottomSheetVisible = mutableStateOf(false)
  override val editCategoryViewModel = EditCategoryViewModelMock()
  override val categories: Observable<List<CategoryModel>> =
    Observable.just(buildCategoryModelList())

  override val uiEvent = mutableStateOf<EditCategoriesUiEvent.UiEvent?>(null)

  override fun onComposition() {}

  override fun onCategoryClick(categoryModel: CategoryModel) {}

  override fun eventConsumed() {}

  override fun onModalBottomSheetVisible() {}

  override fun onModalBottomSheetNotVisible() {}

  override fun onFloatingActionButtonClick() {}

  private fun buildCategoryModelList(): List<CategoryModel> {
    val list = mutableListOf<CategoryModel>()
    list.add(CategoryModel(1, "Super", ""))
    list.add(CategoryModel(2, "Café", ""))
    list.add(CategoryModel(3, "Restau", ""))
    return list
  }
}