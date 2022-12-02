package neuro.expenses.register.domain.dto

data class ProductDto(
  val id: Long,
  val description: String,
  val variableAmount: Boolean,
  val iconUrl: String
)