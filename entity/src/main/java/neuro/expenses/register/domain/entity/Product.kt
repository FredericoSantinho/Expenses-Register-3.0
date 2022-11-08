package neuro.expenses.register.domain.entity

data class Product(
  val id: Long,
  val description: String,
  val category: String,
  val price: Double,
  val defaultAmount: Double,
  val iconUrl: String = ""
)