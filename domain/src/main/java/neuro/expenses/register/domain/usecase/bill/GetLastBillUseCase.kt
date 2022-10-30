package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.entity.Bill
import java.util.*

interface GetLastBillUseCase {
  fun getLastBill(): Observable<Optional<Bill>>
}