package neuro.expenses.register.viewmodel.model.search

data class ProductSearchSuggestionModel(
  val description: String,
  val price: String,
  val iconUrl: String
) : SearchSuggestion