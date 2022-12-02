package neuro.expenses.register.domain.repository.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.BillDto

interface GetBillRepository {
  fun getBill(id: Long): Single<BillDto>
}