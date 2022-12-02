package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto

interface GetLastBillUseCase {
  fun getLastBill(): Maybe<BillDto>
}