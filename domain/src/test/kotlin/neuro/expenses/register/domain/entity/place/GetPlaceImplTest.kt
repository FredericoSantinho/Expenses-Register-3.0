package neuro.expenses.register.domain.entity.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.place.GetPlaceRepository
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.expenses.register.entity.test.mocks.placeMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetPlaceImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getPlace() {
    val getPlaceRepository = mock<GetPlaceRepository>()

    val getPlace = GetPlaceImpl(getPlaceRepository)

    val nameLowercase = String()

    val placeDto = placeDtoMock()
    val expectedPlace = placeMock()

    whenever(getPlaceRepository.getPlace(nameLowercase)).thenReturn(
      Maybe.just(placeDto)
        .observeSubscription()
    )

    getPlace.getPlace(nameLowercase)
      .test()
      .assertValue(expectedPlace)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
