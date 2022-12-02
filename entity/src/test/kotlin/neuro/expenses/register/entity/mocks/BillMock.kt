package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.model.Bill
import neuro.expenses.register.entity.model.BillItem
import neuro.expenses.register.entity.model.Place
import java.util.*

fun billMock(
  id: Long = 1L,
  calendar: Calendar = Calendar.getInstance(),
  place: Place = placeMock(),
  billItems: List<BillItem> = billItemsMock(),
  isOpen: Boolean = true,
  iconUrl: String = ""
): Bill {
  return Bill(id, calendar, place, 1.0, billItems, isOpen = isOpen, iconUrl = iconUrl)
}