package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.BillItem

class CalculateBillTotalImpl : CalculateBillTotal {
  override fun getTotal(billItems: List<BillItem>): Double {
    return billItems.map { it.placeProduct.price * it.amount }.reduce({ acc, d -> acc + d })
  }
}