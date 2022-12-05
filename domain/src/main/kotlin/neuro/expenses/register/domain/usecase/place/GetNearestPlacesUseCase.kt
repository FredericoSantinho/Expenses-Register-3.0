package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto

interface GetNearestPlacesUseCase {
  /**
   * Get nearest places.
   *
   * @param latLngDto Reference location.
   * @param limit maximum results.
   * @return Single with a list of nearest Places.
   */
  fun getNearestPlaces(latLngDto: LatLngDto, limit: Int): Single<List<PlaceDto>>
}