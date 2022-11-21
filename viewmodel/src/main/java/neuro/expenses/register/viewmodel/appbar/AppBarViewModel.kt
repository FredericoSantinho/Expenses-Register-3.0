package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.model.search.SearchSuggestionModel
import neuro.expenses.register.viewmodel.search.SearchViewModel

class AppBarViewModel(val searchViewModel: SearchViewModel) {
  val dataIn: MutableState<List<SearchSuggestionModel>> = mutableStateOf(emptyList())
  val searchEnabled = mutableStateOf(false)
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  val title = mutableStateOf("")

  fun onSettingsButton() {
    _uiEvent.value = UiEvent.NavigateToSettings()
  }

  fun onSearchButton() {
    _uiEvent.value = UiEvent.FocusSearch()
    searchViewModel.showSearch.value = true
  }

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun reset(title: String) {
    this.title.value = title
    dataIn.value = emptyList()
    searchEnabled.value = false
  }

  fun clearSearch() {
    searchViewModel.query.value = ""
    searchViewModel.onCloseSearchButton()
  }

  fun enableSearch() {
    searchEnabled.value = true
  }
}

sealed class UiEvent {
  class NavigateToSettings : UiEvent()
  class FocusSearch : UiEvent()
}