package neuro.expenses.register.entity.model

data class Product(
  val id: Long,
  val description: String,
  val variableAmount: Boolean,
  val iconUrl: String
)