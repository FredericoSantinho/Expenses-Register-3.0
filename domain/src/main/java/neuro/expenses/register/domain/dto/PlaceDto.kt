package neuro.expenses.register.domain.dto

data class PlaceDto(
  val id: Long,
  val name: String,
  val products: List<ProductDto>,
  val latLngDto: LatLngDto
)