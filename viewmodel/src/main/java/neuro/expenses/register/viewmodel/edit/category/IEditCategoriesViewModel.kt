package neuro.expenses.register.viewmodel.edit.category

import androidx.compose.runtime.State
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.model.CategoryModel

interface IEditCategoriesViewModel {
  val editCategoryViewModel: IEditCategoryViewModel
  val categories: Observable<List<CategoryModel>>

  val uiEvent: State<EditCategoriesUiEvent.UiEvent?>

  fun onComposition()
  fun onCategoryClick(categoryModel: CategoryModel)
  fun eventConsumed()
  fun onModalBottomSheetVisible()
  fun onModalBottomSheetNotVisible()
  fun onFloatingActionButtonClick()
}