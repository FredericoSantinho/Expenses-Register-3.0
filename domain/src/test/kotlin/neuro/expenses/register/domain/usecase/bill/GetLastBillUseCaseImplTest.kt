package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.bill.GetLastBillRepository
import neuro.expenses.register.entity.mocks.billDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetLastBillUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getLastBill() {
    val getLastBillRepository = mock<GetLastBillRepository>()

    val getLastBillUseCase = GetLastBillUseCaseImpl(getLastBillRepository)

    val expectedBillDto = billDtoMock()

    whenever(getLastBillRepository.getLastBill()).thenReturn(
      Maybe.just(expectedBillDto)
        .observeSubscription()
    )

    getLastBillUseCase.getLastBill()
      .test()
      .assertValue(expectedBillDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
