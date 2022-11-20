package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.livedata.SingleLiveEvent
import neuro.expenses.register.viewmodel.search.SearchViewModel

class AppBarViewModel(val searchViewModel: SearchViewModel) {
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  val title = mutableStateOf("Take")

  fun onSettingsButton() {
    _uiEvent.value = UiEvent.NavigateToSettings()
  }

  fun onSearchButton() {
    _uiEvent.value = UiEvent.FocusSearch()
    searchViewModel.showSearch.value = true
  }
}

sealed class UiEvent {
  class NavigateToSettings : UiEvent()
  class FocusSearch : UiEvent()
}