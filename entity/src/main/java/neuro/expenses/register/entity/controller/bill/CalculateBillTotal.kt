package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.BillItem

interface CalculateBillTotal {
  fun getTotal(billItems: List<BillItem>): Double
}