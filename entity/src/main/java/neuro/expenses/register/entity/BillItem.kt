package neuro.expenses.register.entity

data class BillItem(
  val id: Long,
  val product: Product,
  val amount: Double,
  val total: Double = product.price * amount
)