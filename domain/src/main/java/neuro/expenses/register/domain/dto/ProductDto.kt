package neuro.expenses.register.domain.dto

data class ProductDto(
  val id: Long,
  val description: String,
  val category: String,
  val price: Double,
  val iconUrl: String = ""
)