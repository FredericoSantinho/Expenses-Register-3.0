package neuro.expenses.register.domain.repository.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto

interface GetLastBillRepository {
  /**
   * Get last Bill.
   *
   * @return Maybe with the last BillDto.
   */
  fun getLastBill(): Maybe<BillDto>
}