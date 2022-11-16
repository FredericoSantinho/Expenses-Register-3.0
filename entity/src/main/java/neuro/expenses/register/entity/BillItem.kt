package neuro.expenses.register.entity

data class BillItem(
  val id: Long,
  val placeProduct: PlaceProduct,
  val amount: Double,
  val total: Double = placeProduct.price * amount
)