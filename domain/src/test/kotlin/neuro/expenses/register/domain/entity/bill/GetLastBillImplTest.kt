package neuro.expenses.register.domain.entity.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.entity.mocks.billDtoMock
import neuro.expenses.register.entity.test.mocks.billMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

internal class GetLastBillImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getLastBill() {
    val getLastBillUseCase = mock<GetLastBillUseCase>()

    val getLastBillImpl = GetLastBillImpl(getLastBillUseCase)

    val calendar = Calendar.getInstance()
    val billDto = billDtoMock(calendar = calendar)
    val expectedBill = billMock(calendar = calendar)

    whenever(getLastBillUseCase.getLastBill()).thenReturn(
      Maybe.just(billDto)
        .observeSubscription()
    )

    getLastBillImpl.getLastBill()
      .test()
      .assertValue(expectedBill)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
