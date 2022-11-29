package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Expense
import java.util.*

fun expenseMock(i: Long = 1L, cat: String = "cat", place: String = "place"): Expense {
  return Expense("desc $i", cat, place, 1.0, 2.0, Calendar.getInstance())
}
