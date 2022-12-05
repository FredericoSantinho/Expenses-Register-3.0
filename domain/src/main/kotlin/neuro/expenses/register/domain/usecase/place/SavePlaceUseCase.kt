package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface SavePlaceUseCase {
  /**
   * Save a Place.
   *
   * @param placeDto Place to save.
   * @return Completable that completes if operation succeeds.
   */
  fun savePlace(placeDto: PlaceDto): Completable
}