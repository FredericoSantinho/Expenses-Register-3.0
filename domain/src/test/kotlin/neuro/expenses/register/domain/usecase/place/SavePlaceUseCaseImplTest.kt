package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.place.SavePlaceRepository
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SavePlaceUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun savePlace() {
    val savePlaceRepository = mock<SavePlaceRepository>()

    val savePlaceUseCase = SavePlaceUseCaseImpl(savePlaceRepository)

    val placeDto = placeDtoMock()

    whenever(savePlaceRepository.savePlace(placeDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    savePlaceUseCase.savePlace(placeDto)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
