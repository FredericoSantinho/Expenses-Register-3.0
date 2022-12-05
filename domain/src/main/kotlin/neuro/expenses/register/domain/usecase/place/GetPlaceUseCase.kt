package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto

interface GetPlaceUseCase {
  /**
   * Get Place.
   *
   * @param placeId Place id.
   * @return Maybe with the Place.
   */
  fun getPlace(placeId: Long): Maybe<PlaceDto>
}