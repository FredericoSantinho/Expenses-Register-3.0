package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.dto.BillDto

interface SortBills {
  /**
   * Sort a list of Bills by calendar in reverse order.
   *
   * @param bills list of BillDto to sort.
   * @return sorted BillDto list.
   */
  fun sortBills(bills: List<BillDto>): List<BillDto>
}