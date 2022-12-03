package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.place.GetPlaceRepository
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetPlaceUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getPlace() {
    val getPlaceRepository = mock<GetPlaceRepository>()

    val getPlaceUseCase = GetPlaceUseCaseImpl(getPlaceRepository)

    val placeId = 1L
    val expectedPlaceDto = placeDtoMock()

    whenever(getPlaceRepository.getPlace(placeId)).thenReturn(
      Maybe.just(expectedPlaceDto)
        .observeSubscription()
    )

    getPlaceUseCase.getPlace(placeId)
      .test()
      .assertValue(expectedPlaceDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
