package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.location.GetCurrentLocation
import neuro.expenses.register.entity.model.Place

class GetOrCreatePlaceImpl(
  private val getPlace: GetPlace,
  private val generatePlaceId: GeneratePlaceId,
  private val savePlace: SavePlace,
  private val getCurrentLocation: GetCurrentLocation
) : GetOrCreatePlace {
  override fun getOrCreatePlace(name: String): Single<Place> {
    return getPlace.getPlace(name.lowercase())
      .switchIfEmpty(generatePlaceId.newId().flatMap { newId ->
        getCurrentLocation.getCurrentLocation().map { Place(newId, name, emptyList(), it) }
      }.flatMap { savePlace.savePlace(it).andThen(Single.just(it)) })
  }
}