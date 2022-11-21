package neuro.expenses.register.viewmodel.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.MoreItem
import neuro.expenses.register.viewmodel.appbar.MoreItemText
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.main.MainViewModel

class EditViewModel(private val mainViewModel: MainViewModel) : ViewModel() {
  private val appBarViewModel = AppBarViewModel()

  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  init {
    appBarViewModel.moreItems.value = buildMoreItemsList()
  }

  private fun buildMoreItemsList(): List<MoreItem> {
    val list: MutableList<MoreItem> = mutableListOf()
    list.add(
      MoreItem(EditProductMoreItem, { _uiEvent.value = UiEvent.NavigateTo(Directions.product) })
    )
    list.add(
      MoreItem(EditPlaceProductMoreItem,
        { _uiEvent.value = UiEvent.NavigateTo(Directions.placeProduct) })
    )
    list.add(
      MoreItem(EditCategoryMoreItem, { _uiEvent.value = UiEvent.NavigateTo(Directions.category) })
    )
    list.add(MoreItem(EditPlaceMoreItem, { _uiEvent.value = UiEvent.NavigateTo(Directions.place) }))
    return list
  }

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun onComposition() {
    mainViewModel.appBarViewModelState.value = appBarViewModel
  }
}

sealed class UiEvent(val directions: Directions) {
  class NavigateTo(directions: Directions) : UiEvent(directions)
}

enum class Directions(val screenRoute: String) {
  product("product"), placeProduct("placeProduct"), category("category"), place("place")
}

object EditProductMoreItem : MoreItemText()
object EditPlaceProductMoreItem : MoreItemText()
object EditCategoryMoreItem : MoreItemText()
object EditPlaceMoreItem : MoreItemText()