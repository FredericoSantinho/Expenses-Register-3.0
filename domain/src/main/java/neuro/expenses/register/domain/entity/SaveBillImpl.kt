package neuro.expenses.register.domain.entity

import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.SaveBillRepository
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.controller.bill.SaveBill

class SaveBillImpl(private val saveBillRepository: SaveBillRepository) : SaveBill {
  override fun save(bill: Bill) {
    saveBillRepository.saveBill(bill.toDomain())
  }
}