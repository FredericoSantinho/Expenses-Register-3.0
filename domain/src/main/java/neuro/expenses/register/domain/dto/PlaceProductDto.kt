package neuro.expenses.register.domain.dto

data class PlaceProductDto(
  val id: Long,
  val productDto: ProductDto,
  val category: CategoryDto,
  val price: Double,
  val placeId: Long
)