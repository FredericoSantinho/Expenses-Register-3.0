package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto

interface GetNearestPlaceUseCase {
  /**
   * Get nearest Place.
   *
   * @return Maybe with the nearest Place available based on current location.
   */
  fun getNearestPlace(): Maybe<PlaceDto>
}