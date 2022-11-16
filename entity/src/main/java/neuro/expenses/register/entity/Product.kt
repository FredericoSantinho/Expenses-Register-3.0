package neuro.expenses.register.entity

data class Product(
  val id: Long,
  val description: String,
  val category: Category,
  val price: Double,
  val placeId: Long,
  val variableAmount: Boolean,
  val iconUrl: String = ""
)