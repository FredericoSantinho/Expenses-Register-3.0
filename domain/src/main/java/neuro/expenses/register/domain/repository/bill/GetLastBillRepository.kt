package neuro.expenses.register.domain.repository.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto

interface GetLastBillRepository {
  fun getLastBill(): Maybe<BillDto>
}