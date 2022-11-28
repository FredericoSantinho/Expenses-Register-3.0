package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.location.GetCurrentLocation
import neuro.expenses.register.entity.mocks.latLngMock
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertFalse

internal class GetOrCreatePlaceImplTest {
  @Test
  fun test() {
    val getPlace = mock<GetPlace>()
    val generatePlaceId = mock<GeneratePlaceId>()
    val savePlace = mock<SavePlace>()
    val getCurrentLocation = mock<GetCurrentLocation>()
    val placeName = "name"
    val placeId: Long = 1
    val expectedPlace = Place(placeId, placeName, emptyList(), latLngMock())

    val getOrCreatePlace =
      GetOrCreatePlaceImpl(getPlace, generatePlaceId, savePlace, getCurrentLocation)

    whenever(getPlace.getPlace(placeName)).thenReturn(Maybe.empty())
    whenever(generatePlaceId.newId()).thenReturn(Single.just(placeId))
    whenever(getCurrentLocation.getCurrentLocation()).thenReturn(Single.just(latLngMock()))
    whenever(savePlace.savePlace(expectedPlace)).thenReturn(Completable.complete())

    verifyNoInteractions(savePlace)

    // Test new place
    var testObserver = getOrCreatePlace.getOrCreatePlace(placeName).test()
    testObserver.assertValue(expectedPlace)

    verify(savePlace, times(1)).savePlace(eq(expectedPlace))
    verify(getCurrentLocation, times(1)).getCurrentLocation()

    // Test existing place
    val generatePlaceIdSubject = PublishSubject.create<Long>()
    whenever(getPlace.getPlace(placeName)).thenReturn(Maybe.just(expectedPlace))
    whenever(generatePlaceId.newId()).thenReturn(generatePlaceIdSubject.singleOrError())

    testObserver = getOrCreatePlace.getOrCreatePlace(placeName).test()
    testObserver.assertValue(expectedPlace)

    verify(savePlace, times(1)).savePlace(eq(expectedPlace))
    verify(getCurrentLocation, times(1)).getCurrentLocation()
    assertFalse { generatePlaceIdSubject.hasObservers() }
  }
}
