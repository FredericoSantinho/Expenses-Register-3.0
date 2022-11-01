package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.ObserveLastBillRepository

class ObserveLastBillUseCaseImpl(private val lastBillRepository: ObserveLastBillRepository) :
  ObserveLastBillUseCase {
  override fun observeLastBill(): Observable<BillDto> {
    return lastBillRepository.observeLastBill()
  }
}