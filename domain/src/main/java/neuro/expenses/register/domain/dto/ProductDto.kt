package neuro.expenses.register.domain.dto

data class ProductDto(
  val id: Long,
  val description: String,
  val category: CategoryDto,
  val price: Double,
  val iconUrl: String,
  val placeId: Long,
  val variableAmount: Boolean
)