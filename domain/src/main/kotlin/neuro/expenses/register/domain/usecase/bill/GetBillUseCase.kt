package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.BillDto

interface GetBillUseCase {
  /**
   * Get a Bill.
   *
   * @param id Bill id.
   * @return Single with the BillDto.
   */
  fun getBill(id: Long): Single<BillDto>
}