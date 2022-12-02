package neuro.expenses.register.domain.dto

import java.util.*

data class ExpenseDto(
  val description: String,
  val category: String,
  val place: String,
  val price: Double,
  val amount: Double,
  val calendar: Calendar
)