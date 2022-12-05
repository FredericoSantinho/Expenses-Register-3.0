package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto

interface ObserveNearestPlacesUseCase {
  /**
   * Observe nearest places.
   *
   * @param latLngDto Reference location.
   * @param limit maximum results.
   * @return Observable that will emit a list with the nearest places each time places change.
   */
  fun observeNearestPlaces(latLngDto: LatLngDto, limit: Int): Observable<List<PlaceDto>>
}