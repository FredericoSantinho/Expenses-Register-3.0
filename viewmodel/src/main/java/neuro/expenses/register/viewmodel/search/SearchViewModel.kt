package neuro.expenses.register.viewmodel.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SearchViewModel(
  val query: MutableState<String> = mutableStateOf(""),
  val showSearch: MutableState<Boolean> = mutableStateOf(false)
) {
  fun closeSearch() {
    showSearch.value = false
  }

  fun onCloseSearchButton() {
    if (query.value.isEmpty()) {
      closeSearch()
    } else {
      query.value = ""
    }
  }
}