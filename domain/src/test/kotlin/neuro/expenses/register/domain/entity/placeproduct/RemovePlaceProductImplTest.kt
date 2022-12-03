package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.place.RemovePlaceProductRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RemovePlaceProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun removePlaceProduct() {
    val removePlaceProductRepository = mock<RemovePlaceProductRepository>()

    val removePlaceProduct = RemovePlaceProductImpl(removePlaceProductRepository)

    val placeId = 1L
    val placeProductId = 2L

    whenever(removePlaceProductRepository.removePlaceProduct(placeId, placeProductId)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    removePlaceProduct.removePlaceProduct(placeId, placeProductId)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
