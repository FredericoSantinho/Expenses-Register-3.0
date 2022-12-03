package neuro.expenses.register.domain.entity.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.place.AddPlaceProductRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class AddPlaceProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun addPlaceProduct() {
    val addPlaceProductRepository = mock<AddPlaceProductRepository>()

    val addPlaceProduct = AddPlaceProductImpl(addPlaceProductRepository)

    val placeId = 1L
    val placeProductId = 2L

    whenever(addPlaceProductRepository.addPlaceProduct(placeId, placeProductId)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    addPlaceProduct.addPlaceProduct(placeId, placeProductId)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
