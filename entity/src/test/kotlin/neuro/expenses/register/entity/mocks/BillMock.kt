package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Bill
import java.util.*

fun billMock(id: Long = 1L): Bill {
  return Bill(id, Calendar.getInstance(), placeMock(), 1.0, billItemsMock())
}