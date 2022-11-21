package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.model.search.SearchSuggestion
import neuro.expenses.register.viewmodel.search.SearchViewModel

class AppBarViewModel(val searchViewModel: SearchViewModel) {
  val dataIn: MutableState<List<SearchSuggestion>> = mutableStateOf(emptyList())
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  val title = mutableStateOf("Take")

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
}

sealed class UiEvent {
  class NavigateToSettings : UiEvent()
  class FocusSearch : UiEvent()
}