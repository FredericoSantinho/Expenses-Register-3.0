package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.model.search.SearchSuggestionModel
import neuro.expenses.register.viewmodel.search.SearchViewModel

class AppBarViewModel() {
  val searchViewModel: SearchViewModel

  val dataIn: MutableState<List<SearchSuggestionModel>> = mutableStateOf(emptyList())
  val searchEnabled = mutableStateOf(false)
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  val title = mutableStateOf("")
  private val _queryObservable: BehaviorRelay<String> = BehaviorRelay.create()
  private val queryObservable: Observable<String> = _queryObservable
  val query: MutableState<String> = mutableStateOf("")

  init {
    searchViewModel = SearchViewModel(onCloseButton = { onCloseSearchButton() })
  }

  fun query(): Observable<String> {
    return queryObservable
  }

  fun onSettingsButton() {
    _uiEvent.value = UiEvent.NavigateToSettings()
  }

  fun onSearchButton() {
    _uiEvent.value = UiEvent.FocusSearch()
    searchViewModel.showSearch.value = true
    searchEnabled.value = false
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

  fun onValueChange(query: String) {
    _queryObservable.accept(query)
    this.query.value = query
  }

  fun onCloseSearchButton() {
    if (searchViewModel.showSearch.value) {
      onValueChange("")
    } else {
      enableSearch()
    }
  }
}

sealed class UiEvent {
  class NavigateToSettings : UiEvent()
  class FocusSearch : UiEvent()
}