package neuro.expenses.register.domain.entity

data class Product(
  val description: String,
  val category: String,
  val price: Double,
  val iconUrl: String = ""
)