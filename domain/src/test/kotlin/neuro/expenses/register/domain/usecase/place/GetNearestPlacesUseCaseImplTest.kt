package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.entity.mocks.latLngDtoMock
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetNearestPlacesUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getNearestPlaces() {
    val observeNearestPlacesUseCase = mock<ObserveNearestPlacesUseCase>()

    val getNearestPlacesUseCase = GetNearestPlacesUseCaseImpl(observeNearestPlacesUseCase)

    val latLngDto = latLngDtoMock()
    val limit = 1
    val expectedPlaceDtoList = listOf(placeDtoMock())

    whenever(observeNearestPlacesUseCase.observeNearestPlaces(latLngDto, limit)).thenReturn(
      Observable.just(expectedPlaceDtoList)
        .observeSubscription()
    )

    getNearestPlacesUseCase.getNearestPlaces(latLngDto, limit)
      .test()
      .assertValue(expectedPlaceDtoList)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }

  @Test
  fun getNearestPlacesNotSupposedToHappenError() {
    val observeNearestPlacesUseCase = mock<ObserveNearestPlacesUseCase>()

    val getNearestPlacesUseCase = GetNearestPlacesUseCaseImpl(observeNearestPlacesUseCase)

    val latLngDto = latLngDtoMock()
    val limit = 1

    whenever(observeNearestPlacesUseCase.observeNearestPlaces(latLngDto, limit)).thenReturn(
      Observable.empty<List<PlaceDto>>()
        .observeSubscription()
    )

    getNearestPlacesUseCase.getNearestPlaces(latLngDto, limit)
      .test()
      .assertNoValues()
      .assertNotComplete()
      .assertError(NoSuchElementException::class.java)

    assertSubscription()
  }
}
