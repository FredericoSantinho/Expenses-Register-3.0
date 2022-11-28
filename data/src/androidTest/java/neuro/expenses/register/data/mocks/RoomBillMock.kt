package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.bill.RoomBill
import java.util.*

fun roomBillMock(billId: Long = incrementer.getAndIncrement(), placeId: Long): RoomBill {
  return RoomBill(billId, placeId, Calendar.getInstance(), 1.0, true, "iconUrl")
}