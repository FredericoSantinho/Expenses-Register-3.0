package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto

interface GetPlaceRepository {
  fun getPlace(placeName: String): Maybe<PlaceDto>
}