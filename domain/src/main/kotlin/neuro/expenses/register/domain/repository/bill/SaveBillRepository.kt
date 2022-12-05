package neuro.expenses.register.domain.repository.bill

import neuro.expenses.register.domain.dto.BillDto

interface SaveBillRepository {
  /**
   * Save a Bill.
   *
   * @param billDto Bill to save.
   */
  fun saveBill(billDto: BillDto)
}