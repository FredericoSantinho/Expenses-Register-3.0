package neuro.expenses.register.entity.model

import java.util.*

data class Expense(
  val description: String,
  val category: String,
  val place: String,
  val price: Double,
  val amount: Double,
  val calendar: Calendar
)