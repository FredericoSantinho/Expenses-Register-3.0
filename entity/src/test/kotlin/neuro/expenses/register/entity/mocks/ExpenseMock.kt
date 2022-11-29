package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Expense
import java.util.*

fun expenseMock(
  i: Long = 1L,
  description: String = "desc $i",
  cat: String = "cat",
  place: String = "place",
  amount: Double = 2.0
): Expense {
  return Expense(description, cat, place, 1.0, amount, Calendar.getInstance())
}
