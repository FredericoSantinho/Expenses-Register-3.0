package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveBillsUseCase {
  /**
   * Observe all Bills.
   *
   * @return Observable that will emit a list will all Bills each time Bills change.
   */
  fun observeBills(): Observable<List<BillDto>>
}