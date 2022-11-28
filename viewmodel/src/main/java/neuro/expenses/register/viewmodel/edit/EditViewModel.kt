package neuro.expenses.register.viewmodel.edit

import androidx.lifecycle.ViewModel
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.Title
import neuro.expenses.register.viewmodel.edit.EditUiEvent.Destination
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class EditViewModel(private val scaffoldViewModelState: ScaffoldViewModelState) : ViewModel() {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()
  val pages = buildPages()

  private val _uiEvent = EditUiEvent()
  val uiEvent = _uiEvent.uiEvent

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  fun onComposition() {
    setupScaffold()
  }

  fun onPageClick(index: Int) {
    when (index) {
      0 -> _uiEvent.navigateToEditCategory()
      1 -> _uiEvent.navigateToEditProduct()
      2 -> _uiEvent.navigateToEditPlaceProduct()
      3 -> _uiEvent.navigateToEditPlace()
    }
  }

  private fun setupScaffold() {
    scaffoldViewModelState.reset()
    appBarViewModel.title.value = EditTitle
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }

  private fun buildPages() = listOf(
    Destination.categories, Destination.products, Destination.placeProducts, Destination.places
  )
}

object EditTitle : Title()