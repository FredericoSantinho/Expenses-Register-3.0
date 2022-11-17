package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.LatLng
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.location.GetCurrentLocation

private val zeroLatLng = LatLng(0.0, 0.0)

class GetOrCreatePlaceImpl(
  private val getPlace: GetPlace,
  private val generatePlaceId: GeneratePlaceId,
  private val savePlace: SavePlace,
  private val getCurrentLocation: GetCurrentLocation
) : GetOrCreatePlace {
  override fun getOrCreatePlace(name: String): Single<Place> {
    return getPlace.getPlace(name.lowercase()).switchIfEmpty(
      generatePlaceId.newId().flatMap { newId ->
        getCurrentLocation.getCurrentLocation().defaultIfEmpty(zeroLatLng)
          .map { Place(newId, name, emptyList(), it) }
      }
        .flatMap { savePlace.savePlace(it).andThen(Single.just(it)) })
  }
}