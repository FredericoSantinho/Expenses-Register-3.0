package neuro.expenses.register.domain.dto

data class ProductDto(
  val description: String,
  val category: String,
  val price: Double,
  val iconUrl: String = ""
)