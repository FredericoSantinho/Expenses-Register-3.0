package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.Expense
import java.util.*

fun expenseMock(
  i: Long = 1L,
  description: String = "description $i",
  category: String = "category",
  place: String = "place",
  price: Double = 1.0,
  amount: Double = 2.0,
  calendar: Calendar = buildCalendar()
): Expense {
  return Expense(description, category, place, price, amount, calendar)
}

private fun buildCalendar(): Calendar {
  val calendar = Calendar.getInstance()
  calendar.time = Date(0)
  return calendar
}