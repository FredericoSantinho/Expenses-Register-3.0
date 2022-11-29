package neuro.expenses.register.domain.entity.bill

import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.bill.SaveBillRepository
import neuro.expenses.register.entity.bill.SaveBill
import neuro.expenses.register.entity.model.Bill

class SaveBillImpl(private val saveBillRepository: SaveBillRepository) : SaveBill {
  override fun save(bill: Bill) {
    saveBillRepository.saveBill(bill.toDomain())
  }
}