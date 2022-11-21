package neuro.expenses.register.ui.common.composables.appbar.suggestions.filter

interface StringFilter {
  fun filter(query: String, s: String): Boolean
}