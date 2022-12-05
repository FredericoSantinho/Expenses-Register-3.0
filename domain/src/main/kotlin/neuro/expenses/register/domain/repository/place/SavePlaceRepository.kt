package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface SavePlaceRepository {
  /**
   * Save a Place.
   *
   * @param placeDto Place to save.
   * @return Completable that completes if operation succeeds.
   */
  fun savePlace(placeDto: PlaceDto): Completable
}