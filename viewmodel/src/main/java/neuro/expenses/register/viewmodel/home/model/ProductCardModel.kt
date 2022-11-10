package neuro.expenses.register.viewmodel.home.model

data class ProductCardModel(
  val description: String,
  val category: String,
  val place: String,
  val price: String,
  val amount: Double,
  val iconUrl: String
)