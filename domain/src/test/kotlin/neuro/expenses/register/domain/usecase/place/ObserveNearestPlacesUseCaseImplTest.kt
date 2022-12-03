package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.place.ObservePlacesRepository
import neuro.expenses.register.entity.location.CalculateDistanceService
import neuro.expenses.register.entity.mocks.latLngDtoMock
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ObserveNearestPlacesUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun observeNearestPlaces() {
    val observePlacesRepository = mock<ObservePlacesRepository>()
    val calculateDistanceService = mock<CalculateDistanceService>()

    val observeNearestPlacesUseCase = ObserveNearestPlacesUseCaseImpl(
      observePlacesRepository,
      calculateDistanceService
    )

    val latLngDto = latLngDtoMock()
    val limit = 1
    val placeLatLngDto1 = latLngDtoMock(1.0)
    val placeLatLngDto2 = latLngDtoMock(2.0)
    val placeDto1 = placeDtoMock(1, latLngDto = placeLatLngDto1)
    val placeDto2 = placeDtoMock(2, latLngDto = placeLatLngDto2)
    val expectedPlaceDtoList = listOf(placeDto1)

    whenever(observePlacesRepository.observePlaces()).thenReturn(
      Observable.just(listOf(placeDto1, placeDto2)).observeSubscription()
    )
    whenever(
      calculateDistanceService.calculateDistance(
        latLngDto.toEntity(),
        placeLatLngDto1.toEntity()
      )
    ).thenReturn(
      1.0
    )
    whenever(
      calculateDistanceService.calculateDistance(
        latLngDto.toEntity(),
        placeLatLngDto2.toEntity()
      )
    ).thenReturn(
      2.0
    )

    observeNearestPlacesUseCase.observeNearestPlaces(latLngDto, limit)
      .test()
      .assertValue(expectedPlaceDtoList)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
