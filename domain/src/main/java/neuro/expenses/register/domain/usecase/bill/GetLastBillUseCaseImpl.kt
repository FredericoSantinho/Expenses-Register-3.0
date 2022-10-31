package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto
import java.util.*

class GetLastBillUseCaseImpl : GetLastBillUseCase {
  override fun getLastBill(): Observable<Optional<BillDto>> {
    return Observable.just(Optional.empty())
  }
}