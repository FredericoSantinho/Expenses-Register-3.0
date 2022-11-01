package neuro.expenses.register.domain.dto

data class BillItemDto(
  val product: PricedProductDto,
  val amount: Double,
  val total: Double = product.price * amount
)