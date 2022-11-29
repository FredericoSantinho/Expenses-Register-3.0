package neuro.expenses.register.domain.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.place.GeneratePlaceIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GeneratePlaceIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun generatePlaceId() {
    val generatePlaceIdRepository = mock<GeneratePlaceIdRepository>()
    val generatePlaceId = GeneratePlaceIdImpl(generatePlaceIdRepository)

    whenever(generatePlaceIdRepository.newId()).thenReturn(Single.just(1L).observeSubscription())

    verifyNoInteractions(generatePlaceIdRepository)
    generatePlaceId.newId().test().assertValue(1L)
    assertSubscription()
  }
}