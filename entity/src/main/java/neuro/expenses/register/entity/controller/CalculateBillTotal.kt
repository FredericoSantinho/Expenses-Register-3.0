package neuro.expenses.register.entity.controller

import neuro.expenses.register.entity.BillItem

interface CalculateBillTotal {
  fun getTotal(billItems: List<BillItem>): Double
}