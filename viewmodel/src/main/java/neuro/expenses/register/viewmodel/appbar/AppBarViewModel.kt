package neuro.expenses.register.viewmodel.appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.model.search.SearchSuggestionModel
import neuro.expenses.register.viewmodel.search.SearchViewModel

class AppBarViewModel() {
  val searchViewModel: SearchViewModel = SearchViewModel(onCloseButton = { onCloseSearchButton() })
  val searchHint = mutableStateOf<SearchHint>(SearchHint.Search)
  val moreItems = mutableStateOf<List<MoreItem>>(emptyList())

  val dataIn: MutableState<List<SearchSuggestionModel>> = mutableStateOf(emptyList())
  val searchEnabled = mutableStateOf(false)

  val title = mutableStateOf<Title>(Title.EmptyTitle)
  private val _queryObservable: BehaviorRelay<String> = BehaviorRelay.create()
  val query: Observable<String> = _queryObservable

  private val _uiEvent = AppBarUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    _queryObservable.accept("")
  }

  fun onSettingsButton() {
    _uiEvent.navigateToSettings()
  }

  fun onSearchButton() {
    _uiEvent.focusSearch()
    searchViewModel.showSearch.value = true
    searchEnabled.value = false
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  fun clearSearch() {
    onValueChange("")
    searchViewModel.query.value = ""
    searchViewModel.onCloseSearchButton()
  }

  fun enableSearch() {
    searchEnabled.value = true
  }

  fun onValueChange(query: String) {
    _queryObservable.accept(query)
  }

  fun onCloseSearchButton() {
    if (searchViewModel.showSearch.value) {
      onValueChange("")
    } else {
      enableSearch()
    }
  }
}

abstract class SearchHint() {
  object Search : SearchHint()
}

abstract class Title() {
  object EmptyTitle : Title()
}