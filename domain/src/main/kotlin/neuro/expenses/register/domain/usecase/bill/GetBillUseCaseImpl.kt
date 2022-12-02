package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.GetBillRepository

class GetBillUseCaseImpl(private val getBillRepository: GetBillRepository) : GetBillUseCase {
  override fun getBill(id: Long): Single<BillDto> {
    return getBillRepository.getBill(id)
  }
}