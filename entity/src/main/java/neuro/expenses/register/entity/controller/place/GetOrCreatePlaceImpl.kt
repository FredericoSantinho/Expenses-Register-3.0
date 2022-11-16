package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.LatLng
import neuro.expenses.register.entity.Place

class GetOrCreatePlaceImpl(
  private val getPlace: GetPlace,
  private val generatePlaceId: GeneratePlaceId,
  private val savePlace: SavePlace
) : GetOrCreatePlace {
  override fun getOrCreatePlace(name: String): Single<Place> {
    return getPlace.getPlace(name.lowercase()).switchIfEmpty(
      generatePlaceId.newId().map { newId ->
        Place(newId, name, emptyList(), LatLng(0.0, 0.0))
      }
        .flatMap { savePlace.savePlace(it).andThen(Single.just(it)) })
  }
}