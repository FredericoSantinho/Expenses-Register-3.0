package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveBillsUseCase {
  fun observeBills(): Observable<List<BillDto>>
}