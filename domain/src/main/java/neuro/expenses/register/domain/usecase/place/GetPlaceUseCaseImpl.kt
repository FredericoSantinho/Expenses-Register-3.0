package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.place.GetPlaceRepository

class GetPlaceUseCaseImpl(private val getPlaceRepository: GetPlaceRepository) : GetPlaceUseCase {
  override fun getPlace(placeId: Long): Maybe<PlaceDto> {
    return getPlaceRepository.getPlace(placeId)
  }
}