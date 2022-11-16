package neuro.expenses.register.domain.dto

data class BillItemDto(
  val id: Long,
  val placeProduct: PlaceProductDto,
  val amount: Double,
  val total: Double = placeProduct.price * amount
)