package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.expenses.register.entity.place.PlaceController
import neuro.expenses.register.entity.test.mocks.placeMock
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UpdatePlaceProductUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun updatePlaceProduct() {
    val placeController = mock<PlaceController>()

    val updatePlaceProductUseCase = UpdatePlaceProductUseCaseImpl(placeController)

    val place = placeMock()
    val placeProduct = placeProductMock()

    val placeDto = placeDtoMock()
    val placeProductDto = placeProductDtoMock()

    whenever(placeController.updatePlaceProduct(place, placeProduct)).thenReturn(
      Single.just(place)
        .observeSubscription()
    )

    updatePlaceProductUseCase.updatePlaceProduct(placeDto, placeProductDto)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
