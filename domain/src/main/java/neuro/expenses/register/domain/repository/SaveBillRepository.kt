package neuro.expenses.register.domain.repository

import neuro.expenses.register.domain.dto.BillDto

interface SaveBillRepository {
  fun saveBill(billDto: BillDto)
}