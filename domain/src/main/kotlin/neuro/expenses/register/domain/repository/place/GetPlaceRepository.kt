package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto

interface GetPlaceRepository {
  /**
   * Get Place.
   *
   * @param placeId Place id.
   * @return Maybe with the Place.
   */
  fun getPlace(placeId: Long): Maybe<PlaceDto>

  /**
   * Get Place.
   *
   * @param placeNameLowerCase Place name in lowercase.
   * @return Maybe with the Place.
   */
  fun getPlace(placeNameLowerCase: String): Maybe<PlaceDto>
}