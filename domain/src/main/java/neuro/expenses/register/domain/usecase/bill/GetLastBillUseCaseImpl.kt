package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.entity.Bill
import java.util.*

class GetLastBillUseCaseImpl : GetLastBillUseCase {
  override fun getLastBill(): Observable<Optional<Bill>> {
    return Observable.just(Optional.empty())
  }
}