package neuro.expenses.register.domain.entity.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.usecase.place.SavePlaceUseCase
import neuro.expenses.register.entity.mocks.placeDtoMock
import neuro.expenses.register.entity.test.mocks.placeMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SavePlaceImplTest() : ObserveSubscriptionTest() {
  @Test
  fun savePlace() {
    val savePlaceUseCase = mock<SavePlaceUseCase>()

    val savePlace = SavePlaceImpl(savePlaceUseCase)

    val placeDto = placeDtoMock()
    val place = placeMock()

    whenever(savePlaceUseCase.savePlace(placeDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    savePlace.savePlace(place)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
