package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.location.GetCurrentLocation
import neuro.expenses.register.entity.model.LatLng
import neuro.expenses.register.entity.model.Place
import neuro.expenses.register.entity.test.mocks.latLngMock
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GetOrCreatePlaceImplTest : ObserveSubscriptionTest() {
  @Test
  fun nonExistentPlace() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlace = mock<GetPlace>()
    val generatePlaceId = mock<GeneratePlaceId>()
    val savePlace = mock<SavePlace>()
    val getCurrentLocation = mock<GetCurrentLocation>()
    val placeName = "name"
    val placeId: Long = 1
    val expectedPlace = Place(placeId, placeName, emptyList(), latLngMock())

    val getOrCreatePlace =
      GetOrCreatePlaceImpl(getPlace, generatePlaceId, savePlace, getCurrentLocation)

    whenever(getPlace.getPlace(placeName)).thenReturn(
      Maybe.empty<Place?>().observeSubscription(incrementer, offset)
    )
    whenever(generatePlaceId.newId()).thenReturn(
      Single.just(placeId).observeSubscription(incrementer, offset)
    )
    whenever(getCurrentLocation.getCurrentLocation()).thenReturn(
      Single.just(latLngMock()).observeSubscription(incrementer, offset)
    )
    whenever(savePlace.savePlace(expectedPlace)).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    verifyNoInteractions(savePlace)

    getOrCreatePlace.getOrCreatePlace(placeName).test().assertValue(expectedPlace).assertNoErrors()
      .assertComplete()

    assertSubscriptions(incrementer, offset)
  }

  @Test
  fun existentPlace() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlace = mock<GetPlace>()
    val generatePlaceId = mock<GeneratePlaceId>()
    val savePlace = mock<SavePlace>()
    val getCurrentLocation = mock<GetCurrentLocation>()
    val placeName = "name"
    val placeId: Long = 1
    val expectedPlace = Place(placeId, placeName, emptyList(), latLngMock())

    val getOrCreatePlace =
      GetOrCreatePlaceImpl(getPlace, generatePlaceId, savePlace, getCurrentLocation)

    whenever(getPlace.getPlace(placeName)).thenReturn(
      Maybe.just(expectedPlace).observeSubscription(incrementer, offset)
    )
    whenever(generatePlaceId.newId()).thenReturn(Single.error(IllegalStateException()))

    getOrCreatePlace.getOrCreatePlace(placeName).test().assertValue(expectedPlace).assertNoErrors()
      .assertComplete()

    assertSubscriptions(incrementer, offset)
  }

  @Test
  fun onLocationException() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlace = mock<GetPlace>()
    val generatePlaceId = mock<GeneratePlaceId>()
    val savePlace = mock<SavePlace>()
    val getCurrentLocation = mock<GetCurrentLocation>()
    val placeName = "name"
    val placeId: Long = 1
    val expectedPlace = Place(placeId, placeName, emptyList(), latLngMock(0.0, 0.0))

    val getOrCreatePlace =
      GetOrCreatePlaceImpl(getPlace, generatePlaceId, savePlace, getCurrentLocation)

    whenever(getPlace.getPlace(placeName)).thenReturn(
      Maybe.empty<Place?>().observeSubscription(incrementer, offset)
    )
    whenever(generatePlaceId.newId()).thenReturn(
      Single.just(placeId).observeSubscription(incrementer, offset)
    )
    whenever(getCurrentLocation.getCurrentLocation()).thenReturn(
      Single.error<LatLng>(IllegalStateException()).observeSubscription(incrementer, offset)
    )
    whenever(savePlace.savePlace(expectedPlace)).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    verifyNoInteractions(savePlace)

    getOrCreatePlace.getOrCreatePlace(placeName).test().assertValue(expectedPlace).assertNoErrors()
      .assertComplete()

    assertSubscriptions(incrementer, offset)
  }
}
