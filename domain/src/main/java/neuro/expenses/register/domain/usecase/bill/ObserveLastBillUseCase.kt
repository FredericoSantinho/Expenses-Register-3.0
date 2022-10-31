package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveLastBillUseCase {
  /**
   * @return an Observable that emits the last bill stored. In case there's no Bills yet, it will not emit until one appears.
   */
  fun observeLastBill(): Observable<BillDto>
}