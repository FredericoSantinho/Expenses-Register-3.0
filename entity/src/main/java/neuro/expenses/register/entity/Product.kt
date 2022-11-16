package neuro.expenses.register.entity

data class Product(
  val id: Long,
  val description: String,
  val category: Category,
  val price: Double,
  val defaultAmount: Double,
  val placeId: Long,
  val iconUrl: String = ""
)