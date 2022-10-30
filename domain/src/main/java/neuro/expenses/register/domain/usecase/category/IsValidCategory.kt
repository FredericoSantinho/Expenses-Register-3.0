package neuro.expenses.register.domain.usecase.category

interface IsValidCategory {
  fun isValidCategory(category: String): Boolean
}