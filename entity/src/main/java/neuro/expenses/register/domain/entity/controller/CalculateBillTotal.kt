package neuro.expenses.register.domain.entity.controller

import neuro.expenses.register.domain.entity.BillItem

interface CalculateBillTotal {
  fun getTotal(billItems: List<BillItem>): Double
}