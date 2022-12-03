package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.Bill
import neuro.expenses.register.entity.model.BillItem
import neuro.expenses.register.entity.model.Place
import java.util.*

fun billMock(
  id: Long = 1L,
  calendar: Calendar = buildCalendar(),
  place: Place = placeMock(),
  billItems: List<BillItem> = billItemsMock(),
  isOpen: Boolean = true,
  iconUrl: String = ""
): Bill {
  return Bill(id, place, calendar, 1.0, billItems, iconUrl = iconUrl, isOpen = isOpen)
}

private fun buildCalendar(): Calendar {
  val calendar = Calendar.getInstance()
  calendar.time = Date(0)
  return calendar
}