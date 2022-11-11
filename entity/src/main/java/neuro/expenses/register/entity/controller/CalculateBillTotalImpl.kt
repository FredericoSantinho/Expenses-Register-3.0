package neuro.expenses.register.entity.controller

import neuro.expenses.register.entity.BillItem

class CalculateBillTotalImpl : CalculateBillTotal {
  override fun getTotal(billItems: List<BillItem>): Double {
    return billItems.map { it.product.price * it.amount }.reduce({ acc, d -> acc + d })
  }
}