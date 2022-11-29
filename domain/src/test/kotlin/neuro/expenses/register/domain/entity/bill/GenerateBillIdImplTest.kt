package neuro.expenses.register.domain.entity.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GenerateBillIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GenerateBillIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun generateBillId() {
    val generateBillIdRepository = mock<GenerateBillIdRepository>()
    val generateBillId = GenerateBillIdImpl(generateBillIdRepository)

    whenever(generateBillIdRepository.newId()).thenReturn(Single.just(1L).observeSubscription())

    verifyNoInteractions(generateBillIdRepository)
    generateBillId.newId().test().assertValue(1L)
    assertSubscription()
  }
}