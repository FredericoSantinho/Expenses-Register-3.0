package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.BillItem
import neuro.expenses.register.entity.PlaceProduct

fun billItemMock(id: Long, placeProduct: PlaceProduct = placeProductMock(id)): BillItem {
  return BillItem(id, placeProduct, 2.0)
}

fun billItemsMock(): List<BillItem> {
  return listOf(billItemMock(1), billItemMock(2), billItemMock(3))
}