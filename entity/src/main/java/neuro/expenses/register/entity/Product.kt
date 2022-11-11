package neuro.expenses.register.entity

data class Product(
  val id: Long,
  val description: String,
  val category: String,
  val price: Double,
  val defaultAmount: Double,
  val iconUrl: String = ""
)