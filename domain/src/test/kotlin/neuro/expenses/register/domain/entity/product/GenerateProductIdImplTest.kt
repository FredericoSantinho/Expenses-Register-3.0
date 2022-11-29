package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.product.GenerateProductIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GenerateProductIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun generatePlaceId() {
    val generateProductIdRepository = mock<GenerateProductIdRepository>()
    val generateProductId = GenerateProductIdImpl(generateProductIdRepository)

    whenever(generateProductIdRepository.newId()).thenReturn(Single.just(1L).observeSubscription())

    verifyNoInteractions(generateProductIdRepository)
    generateProductId.newId().test().assertValue(1L)
    assertSubscription()
  }
}