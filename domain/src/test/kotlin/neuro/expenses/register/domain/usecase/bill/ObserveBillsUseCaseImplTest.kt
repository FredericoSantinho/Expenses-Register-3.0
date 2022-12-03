package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.repository.bill.ObserveBillsRepository
import neuro.expenses.register.entity.mocks.billDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ObserveBillsUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun observeBills() {
    val observeBillsRepository = mock<ObserveBillsRepository>()

    val observeBillsUseCase = ObserveBillsUseCaseImpl(observeBillsRepository)

    val expectedBillDtoList = listOf(billDtoMock())

    whenever(observeBillsRepository.observeBills()).thenReturn(
      Observable.just(expectedBillDtoList)
        .observeSubscription()
    )

    observeBillsUseCase.observeBills()
      .test()
      .assertValue(expectedBillDtoList)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
