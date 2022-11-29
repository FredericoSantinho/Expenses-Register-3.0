package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

interface CalculateBillTotal {
  /**
   * Get the total cost of a list of BillItems.
   *
   * @param billItems list of BillItems.
   * @return total cost.
   */
  fun getTotal(billItems: List<BillItem>): Double
}