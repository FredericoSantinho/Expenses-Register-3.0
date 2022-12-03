package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.expenses.register.entity.place.PlaceController
import neuro.expenses.register.entity.test.mocks.placeMock
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class AddPlaceProductUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun addPlaceProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlaceUseCase = mock<GetPlaceUseCase>()
    val placeController = mock<PlaceController>()

    val addPlaceProductUseCase = AddPlaceProductUseCaseImpl(getPlaceUseCase, placeController)

    val placeId = 1L
    val placeDto = placeDtoMock()
    val place = placeMock()
    val placeProduct = placeProductMock()
    val placeProductDto = placeProductDtoMock()

    whenever(getPlaceUseCase.getPlace(placeId)).thenReturn(
      Maybe.just(placeDto).observeSubscription(incrementer, offset)
    )
    whenever(placeController.addPlaceProduct(place, placeProduct)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )

    addPlaceProductUseCase.addPlaceProduct(placeId, placeProductDto)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
