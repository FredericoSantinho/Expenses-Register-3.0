package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.bill.ObserveLastBillRepository
import neuro.expenses.register.entity.mocks.billDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

internal class ObserveLastBillUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun emptyLastBill() {
    val getLastBillUseCase = mock<GetLastBillUseCase>()
    val observeLastBillRepository = mock<ObserveLastBillRepository>()

    val observeLastBillUseCase = ObserveLastBillUseCaseImpl(
      getLastBillUseCase,
      observeLastBillRepository
    )

    val billDto = billDtoMock()

    whenever(getLastBillUseCase.getLastBill()).thenReturn(
      Maybe.empty<BillDto>()
        .observeSubscription()
    )
    whenever(observeLastBillRepository.observeLastBill()).thenReturn(
      Observable.just(billDto)
        .observeSubscription()
    )

    observeLastBillUseCase.observeLastBill()
      .test()
      .assertValues(newDefaultBillDto(), billDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }

  @Test
  fun presentLastBill() {
    val getLastBillUseCase = mock<GetLastBillUseCase>()
    val observeLastBillRepository = mock<ObserveLastBillRepository>()

    val observeLastBillUseCase = ObserveLastBillUseCaseImpl(
      getLastBillUseCase,
      observeLastBillRepository
    )

    val billDto = billDtoMock(1)

    whenever(getLastBillUseCase.getLastBill()).thenReturn(
      Maybe.just(billDto)
        .observeSubscription()
    )
    whenever(observeLastBillRepository.observeLastBill()).thenReturn(
      Observable.just(billDto)
        .observeSubscription()
    )

    observeLastBillUseCase.observeLastBill()
      .test()
      .assertValue(billDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
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
}
