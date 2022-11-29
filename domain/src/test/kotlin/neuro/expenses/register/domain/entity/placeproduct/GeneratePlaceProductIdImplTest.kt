package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.product.GeneratePlaceProductIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GeneratePlaceProductIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun generatePlaceProductId() {
    val generatePlaceProductIdRepository = mock<GeneratePlaceProductIdRepository>()
    val generatePlaceProductId = GeneratePlaceProductIdImpl(generatePlaceProductIdRepository)

    whenever(generatePlaceProductIdRepository.newId()).thenReturn(
      Single.just(1L).observeSubscription()
    )

    verifyNoInteractions(generatePlaceProductIdRepository)
    generatePlaceProductId.newId().test().assertValue(1L)
    assertSubscription()
  }
}