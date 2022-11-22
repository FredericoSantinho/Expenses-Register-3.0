package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.dto.BillDto

interface SortBills {
  fun sortBills(bills: List<BillDto>): List<BillDto>
}