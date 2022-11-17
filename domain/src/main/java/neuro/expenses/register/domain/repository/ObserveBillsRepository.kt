package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveBillsRepository {
  fun observeBills(): Observable<List<BillDto>>
}