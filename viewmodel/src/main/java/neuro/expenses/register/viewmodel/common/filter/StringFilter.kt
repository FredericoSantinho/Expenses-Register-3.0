package neuro.expenses.register.viewmodel.common.filter

interface StringFilter {
  fun filter(query: String, s: String): Boolean
}