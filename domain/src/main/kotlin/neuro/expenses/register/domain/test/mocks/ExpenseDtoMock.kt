package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.ExpenseDto
import java.util.*

fun expenseDtoMock(
  i: Long = 1L,
  description: String = "description $i",
  category: String = "category",
  place: String = "place",
  price: Double = 1.0,
  amount: Double = 2.0,
  calendar: Calendar = buildCalendar()
): ExpenseDto {
  return ExpenseDto(description, category, place, price, amount, calendar)
}

private fun buildCalendar(): Calendar {
  val calendar = Calendar.getInstance()
  calendar.time = Date(0)
  return calendar
}
