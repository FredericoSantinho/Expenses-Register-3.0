package neuro.expenses.register.domain.entity.controller

import neuro.expenses.register.domain.entity.BillItem

class CalculateBillTotalImpl : CalculateBillTotal {
  override fun getTotal(billItems: List<BillItem>): Double {
    return billItems.map { it.product.price * it.amount }.reduce({ acc, d -> acc + d })
  }
}