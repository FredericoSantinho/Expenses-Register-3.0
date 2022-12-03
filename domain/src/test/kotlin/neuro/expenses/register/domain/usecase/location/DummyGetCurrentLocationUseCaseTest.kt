package neuro.expenses.register.domain.usecase.location

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test

internal class DummyGetCurrentLocationUseCaseTest() : ObserveSubscriptionTest() {
  @Test
  fun getCurrentLocation() {

    val detCurrentLocationUseCase = DummyGetCurrentLocationUseCase()

    val expectedLatLngDto = LatLngDto(0.0, 0.0)

    detCurrentLocationUseCase.getCurrentLocation()
      .test()
      .assertValue(expectedLatLngDto)
      .assertComplete()
      .assertNoErrors()
  }
}
