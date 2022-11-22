package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.GetLastBillRepository

class GetLastBillUseCaseImpl(private val getLastBillRepository: GetLastBillRepository) :
  GetLastBillUseCase {
  override fun getLastBill(): Maybe<BillDto> {
    return getLastBillRepository.getLastBill()
  }
}