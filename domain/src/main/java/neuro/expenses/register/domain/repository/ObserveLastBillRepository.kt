package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto

interface ObserveLastBillRepository {
  fun observeLastBill(): Observable<BillDto>
}