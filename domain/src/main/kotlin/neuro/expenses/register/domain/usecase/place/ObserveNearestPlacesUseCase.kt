package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto

interface ObserveNearestPlacesUseCase {
  fun observeNearestPlaces(latLngDto: LatLngDto, limit: Int): Observable<List<PlaceDto>>
}