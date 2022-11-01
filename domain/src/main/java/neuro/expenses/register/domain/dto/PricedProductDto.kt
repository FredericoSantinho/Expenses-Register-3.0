package neuro.expenses.register.domain.dto

data class PricedProductDto(
  val description: String,
  val category: String,
  val price: Double,
  val iconUrl: String = ""
)