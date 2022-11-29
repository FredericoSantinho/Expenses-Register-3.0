package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

interface CalculateBillTotal {
  fun getTotal(billItems: List<BillItem>): Double
}