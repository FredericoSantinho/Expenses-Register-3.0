package neuro.expenses.register.viewmodel.edit

import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.MoreItem
import neuro.expenses.register.viewmodel.appbar.MoreItemText
import neuro.expenses.register.viewmodel.edit.EditMoreItem.*
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class EditViewModel(private val scaffoldViewModelState: ScaffoldViewModelState) : ViewModel() {
  private val appBarViewModel = AppBarViewModel()

  private val _uiEvent = EditUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    appBarViewModel.moreItems.value = buildMoreItemsList()
  }

  private fun buildMoreItemsList(): List<MoreItem> {
    val list: MutableList<MoreItem> = mutableListOf()
    list.add(
      MoreItem(EditCategoryMoreItem, { _uiEvent.navigateToEditCategory() })
    )
    list.add(
      MoreItem(EditProductMoreItem, { _uiEvent.navigateToEditProduct() })
    )
    list.add(
      MoreItem(EditPlaceProductMoreItem, { _uiEvent.navigateToEditPlaceProduct() })
    )
    list.add(MoreItem(EditPlaceMoreItem, { _uiEvent.navigateToEditPlace() }))
    return list
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  fun onComposition() {
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }
}

class EditMoreItem {
  object EditProductMoreItem : MoreItemText()
  object EditPlaceProductMoreItem : MoreItemText()
  object EditCategoryMoreItem : MoreItemText()
  object EditPlaceMoreItem : MoreItemText()
}
