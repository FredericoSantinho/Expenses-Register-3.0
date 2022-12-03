package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.expenses.register.entity.place.PlaceController
import neuro.expenses.register.entity.test.mocks.placeMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RemovePlaceProductUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun removePlaceProduct() {
    val placeController = mock<PlaceController>()

    val removePlaceProductUseCase = RemovePlaceProductUseCaseImpl(placeController)

    val place = placeMock()
    val placeProductId = 1L
    val placeDto = placeDtoMock()

    whenever(placeController.removePlaceProduct(place, placeProductId)).thenReturn(
      Single.just(place)
        .observeSubscription()
    )

    removePlaceProductUseCase.removePlaceProduct(placeDto, placeProductId)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
