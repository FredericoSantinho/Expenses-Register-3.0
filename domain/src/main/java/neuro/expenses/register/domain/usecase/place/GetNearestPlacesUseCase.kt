package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto

interface GetNearestPlacesUseCase {
  fun getNearestPlaces(latLng: LatLngDto, limit: Int): Single<List<PlaceDto>>
}