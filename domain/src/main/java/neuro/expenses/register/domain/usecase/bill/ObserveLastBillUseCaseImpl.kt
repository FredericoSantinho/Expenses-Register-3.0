package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

class ObserveLastBillUseCaseImpl : ObserveLastBillUseCase {
  override fun observeLastBill(): Observable<BillDto> {
    return Observable.just(BillDto("", 0, 0.0))
  }
}