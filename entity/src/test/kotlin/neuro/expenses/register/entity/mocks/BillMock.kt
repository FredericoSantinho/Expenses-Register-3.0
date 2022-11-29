package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.BillItem
import neuro.expenses.register.entity.Place
import java.util.*

fun billMock(
  id: Long = 1L,
  calendar: Calendar = Calendar.getInstance(),
  place: Place = placeMock(),
  billItems: List<BillItem> = billItemsMock(),
  isOpen: Boolean = true
): Bill {
  return Bill(id, calendar, place, 1.0, billItems, isOpen = isOpen)
}