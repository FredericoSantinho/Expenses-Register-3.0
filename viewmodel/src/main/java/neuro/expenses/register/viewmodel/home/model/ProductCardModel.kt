package neuro.expenses.register.viewmodel.home.model

data class ProductCardModel(
  val id: Long,
  val description: String,
  val categoryId: Long,
  val categoryName: String,
  val place: String,
  val price: String,
  val iconUrl: String,
  val amount: Double = 1.0
)