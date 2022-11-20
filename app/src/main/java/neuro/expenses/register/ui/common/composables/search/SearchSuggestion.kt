package neuro.expenses.register.ui.common.composables.search

import androidx.compose.runtime.Composable

interface SearchSuggestion {
  fun filter(value: String): Boolean
  fun text(): String
  @Composable
  fun composable()
  @Composable
  fun titleComposable()
}