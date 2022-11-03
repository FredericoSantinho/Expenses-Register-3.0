package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.repository.SaveBillRepository
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveBillUseCaseImpl(
  private val saveBillRepository: SaveBillRepository,
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val saveProductRepository: SaveProductRepository,
  private val billMapper: BillMapper
) : SaveBillUseCase {
  override fun save(bill: Bill) {
    bill.billItems.forEach {
      val product = it.product
      saveProductRepository.saveProduct(product.description, product.category, product.price)
    }
    saveBillRepository.saveBill(billMapper.map(bill))
  }
}