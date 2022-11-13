package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto

interface GetPlaceUseCase {
  fun getPlace(placeName: String): Maybe<PlaceDto>
}