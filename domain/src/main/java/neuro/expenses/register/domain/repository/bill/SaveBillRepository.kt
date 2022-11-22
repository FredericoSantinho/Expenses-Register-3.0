package neuro.expenses.register.domain.repository.bill

import neuro.expenses.register.domain.dto.BillDto

interface SaveBillRepository {
  fun saveBill(billDto: BillDto)
}