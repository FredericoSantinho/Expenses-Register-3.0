package neuro.expenses.register.entity.model

data class PlaceProduct(
  val id: Long, val product: Product, val category: Category, val price: Double
)