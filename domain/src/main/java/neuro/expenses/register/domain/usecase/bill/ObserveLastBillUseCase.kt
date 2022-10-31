package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto
import java.util.*

interface ObserveLastBillUseCase {
  fun observeLastBill(): Observable<Optional<BillDto>>
}