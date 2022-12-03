package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GetBillRepository
import neuro.expenses.register.entity.mocks.billDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetBillUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getBill() {
    val getBillRepository = mock<GetBillRepository>()

    val getBillUseCase = GetBillUseCaseImpl(getBillRepository)

    val id = 1L
    val expectedBillDto = billDtoMock()

    whenever(getBillRepository.getBill(id)).thenReturn(
      Single.just(expectedBillDto)
        .observeSubscription()
    )

    getBillUseCase.getBill(id)
      .test()
      .assertValue(expectedBillDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
