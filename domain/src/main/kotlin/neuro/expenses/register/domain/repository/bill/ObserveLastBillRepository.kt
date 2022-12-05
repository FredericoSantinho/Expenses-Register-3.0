package neuro.expenses.register.domain.repository.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveLastBillRepository {
  /**
   * Observe Last Bill.
   *
   * @return an Observable that emits the last bill stored each time it changes. In case there's no
   * Bills yet, no Bill is emitted.
   */
  fun observeLastBill(): Observable<BillDto>
}