package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.bill.ObserveLastBillRepository
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
  val place = PlaceDto(-1, "", emptyList(), LatLngDto(0.0, 0.0))
  return BillDto(-1L, place, calendar, 0.0, isOpen = false)
}
