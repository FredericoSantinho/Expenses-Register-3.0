package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.entity.mocks.latLngDtoMock
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetNearestPlaceUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getNearestPlace() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getNearestPlacesUseCase = mock<GetNearestPlacesUseCase>()
    val getCurrentLocationUseCase = mock<GetCurrentLocationUseCase>()

    val getNearestPlaceUseCase = GetNearestPlaceUseCaseImpl(
      getNearestPlacesUseCase,
      getCurrentLocationUseCase
    )

    val expectedPlaceDto = placeDtoMock()

    val latLngDto = latLngDtoMock()
    val limit = 1

    whenever(getCurrentLocationUseCase.getCurrentLocation()).thenReturn(
      Single.just(latLngDto).observeSubscription(incrementer, offset)
    )
    whenever(getNearestPlacesUseCase.getNearestPlaces(latLngDto, limit)).thenReturn(
      Single.just(listOf(expectedPlaceDto)).observeSubscription(incrementer, offset)
    )

    getNearestPlaceUseCase.getNearestPlace()
      .test()
      .assertValue(expectedPlaceDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }

  @Test
  fun getNearestPlaceEmptyPlaces() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getNearestPlacesUseCase = mock<GetNearestPlacesUseCase>()
    val getCurrentLocationUseCase = mock<GetCurrentLocationUseCase>()

    val getNearestPlaceUseCase = GetNearestPlaceUseCaseImpl(
      getNearestPlacesUseCase,
      getCurrentLocationUseCase
    )

    val latLngDto = latLngDtoMock()
    val limit = 1

    whenever(getCurrentLocationUseCase.getCurrentLocation()).thenReturn(
      Single.just(latLngDto).observeSubscription(incrementer, offset)
    )
    whenever(getNearestPlacesUseCase.getNearestPlaces(latLngDto, limit)).thenReturn(
      Single.just(emptyList<PlaceDto>()).observeSubscription(incrementer, offset)
    )

    getNearestPlaceUseCase.getNearestPlace()
      .test()
      .assertNoValues()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
