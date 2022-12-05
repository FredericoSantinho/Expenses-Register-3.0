package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveLastBillUseCase {
  /**
   * Observe Last Bill.
   *
   * @return an Observable that emits the last bill stored each time it changes. In case there's no
   * Bills yet, it will emit a default closed one, which is only stored in memory.
   */
  fun observeLastBill(): Observable<BillDto>
}