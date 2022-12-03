package neuro.expenses.register.domain.entity.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.service.location.GetCurrentLocationService
import neuro.expenses.register.entity.mocks.latLngDtoMock
import neuro.expenses.register.entity.test.mocks.latLngMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetCurrentLocationImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getCurrentLocation() {
    val getCurrentLocationService = mock<GetCurrentLocationService>()

    val getCurrentLocationImpl = GetCurrentLocationImpl(getCurrentLocationService)

    val latLngDto = latLngDtoMock()
    val expectedLatLng = latLngMock()

    whenever(getCurrentLocationService.getCurrentLocation()).thenReturn(
      Single.just(latLngDto)
        .observeSubscription()
    )

    getCurrentLocationImpl.getCurrentLocation()
      .test()
      .assertValue(expectedLatLng)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
