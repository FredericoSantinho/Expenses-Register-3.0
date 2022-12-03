package neuro.expenses.register.domain.usecase.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.service.location.GetCurrentLocationService
import neuro.expenses.register.entity.mocks.latLngDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetCurrentLocationUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getCurrentLocation() {
    val getCurrentLocationService = mock<GetCurrentLocationService>()

    val getCurrentLocationUseCase = GetCurrentLocationUseCaseImpl(getCurrentLocationService)

    val expectedLatLngDto = latLngDtoMock()

    whenever(getCurrentLocationService.getCurrentLocation()).thenReturn(
      Single.just(expectedLatLngDto)
        .observeSubscription()
    )

    getCurrentLocationUseCase.getCurrentLocation()
      .test()
      .assertValue(expectedLatLngDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
