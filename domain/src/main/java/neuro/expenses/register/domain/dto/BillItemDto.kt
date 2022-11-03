package neuro.expenses.register.domain.dto

data class BillItemDto(
  val id: Long,
  val product: ProductDto,
  val amount: Double,
  val total: Double = product.price * amount
)