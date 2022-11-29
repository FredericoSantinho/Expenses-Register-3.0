package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.BillItem
import neuro.expenses.register.entity.PlaceProduct

fun billItemMock(
  id: Long, placeProduct: PlaceProduct = placeProductMock(id), amount: Double = 1.0
): BillItem {
  return BillItem(id, placeProduct, amount)
}

fun billItemsMock(): MutableList<BillItem> {
  return mutableListOf(billItemMock(1), billItemMock(2), billItemMock(3))
}