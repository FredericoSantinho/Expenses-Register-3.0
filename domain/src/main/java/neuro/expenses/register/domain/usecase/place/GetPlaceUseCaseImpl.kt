package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.GetPlaceRepository

class GetPlaceUseCaseImpl(private val getPlaceRepository: GetPlaceRepository) : GetPlaceUseCase {
  override fun getPlace(placeId: Long): Maybe<PlaceDto> {
    return getPlaceRepository.getPlace(placeId)
  }

  override fun getPlace(placeName: String): Maybe<PlaceDto> {
    return getPlaceRepository.getPlace(placeName.lowercase())
  }
}