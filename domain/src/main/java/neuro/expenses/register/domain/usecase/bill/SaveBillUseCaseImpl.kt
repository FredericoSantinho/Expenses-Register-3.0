package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.repository.SaveBillRepository

class SaveBillUseCaseImpl(
  private val saveBillRepository: SaveBillRepository,
  private val billMapper: BillMapper
) : SaveBillUseCase {
  override fun save(bill: Bill) {
    saveBillRepository.saveBill(billMapper.map(bill))
  }
}