package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.dto.BillDto

class SortBillsImpl : SortBills {
  override fun sortBills(bills: List<BillDto>): List<BillDto> {
    return bills.sortedBy { it.calendar }.reversed()
  }
}