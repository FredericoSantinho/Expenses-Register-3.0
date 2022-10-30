package neuro.expenses.register.domain.usecase.category

class IsValidCategoryImpl : IsValidCategory {
  override fun isValidCategory(category: String): Boolean {
    return true
  }
}