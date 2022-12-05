package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto

interface GetLastBillUseCase {
  /**
   * Get last Bill.
   *
   * @return Maybe with the last BillDto.
   */
  fun getLastBill(): Maybe<BillDto>
}