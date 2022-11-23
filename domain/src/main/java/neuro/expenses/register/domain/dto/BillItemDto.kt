package neuro.expenses.register.domain.dto

data class BillItemDto(
  val id: Long,
  val placeProductDto: PlaceProductDto,
  val amount: Double,
  val total: Double = placeProductDto.price * amount
)