package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.bill.RoomBillItem

fun roomBillItemMock(billItemId: Long = incrementer.getAndIncrement(), placeProductId: Long, billId: Long): RoomBillItem {
  return RoomBillItem(billItemId, 1.0, placeProductId, billId)
}