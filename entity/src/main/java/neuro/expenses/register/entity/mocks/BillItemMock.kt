package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.BillItem

fun billItemMock(id: Long = 1L): BillItem {
  return BillItem(id, placeProductMock(id), 2.0)
}

fun billItemsMock(): List<BillItem> {
  return listOf(billItemMock(1), billItemMock(2), billItemMock(3))
}