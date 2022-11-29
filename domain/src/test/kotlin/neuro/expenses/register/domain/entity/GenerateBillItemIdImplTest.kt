package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.entity.bill.GenerateBillItemIdImpl
import neuro.expenses.register.domain.repository.bill.GenerateBillItemIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GenerateBillItemIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun test() {
    val generateBillItemIdRepository = mock<GenerateBillItemIdRepository>()
    val generateBillItemId = GenerateBillItemIdImpl(generateBillItemIdRepository)

    whenever(generateBillItemIdRepository.newId()).thenReturn(Single.just(1L).observeSubscription())

    verifyNoInteractions(generateBillItemIdRepository)
    generateBillItemId.newId().test().assertValue(1L).assertNoErrors().assertComplete()
    assertSubscription()
  }
}