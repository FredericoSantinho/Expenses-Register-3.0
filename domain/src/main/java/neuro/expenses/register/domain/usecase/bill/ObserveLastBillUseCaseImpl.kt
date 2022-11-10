package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.ObserveLastBillRepository
import java.util.*

private val DEFAULT_BILL_DTO = newDefaultBillDto()

class ObserveLastBillUseCaseImpl(
  private val getLastBillUseCase: GetLastBillUseCase,
  private val observeLastBillRepository: ObserveLastBillRepository
) : ObserveLastBillUseCase {
  override fun observeLastBill(): Observable<BillDto> {
    return getLastBillUseCase.getLastBill().switchIfEmpty(Single.just(DEFAULT_BILL_DTO))
      .flatMapObservable {
        if (it == DEFAULT_BILL_DTO) {
          observeLastBillRepository.observeLastBill()
            .startWith(Single.just(it))
        } else {
          observeLastBillRepository.observeLastBill()
        }
      }
  }
}

private fun newDefaultBillDto(): BillDto {
  val calendar = Calendar.getInstance()
  calendar.time = Date(0)
  calendar.set(Calendar.HOUR, 0)
  calendar.set(Calendar.MINUTE, 0)
  calendar.set(Calendar.DAY_OF_MONTH, 1)
  calendar.set(Calendar.MONTH, 0)
  calendar.set(Calendar.YEAR, 1970)
  return BillDto(-1L, "N/A", calendar, 0.0, emptyList(), false)
}
