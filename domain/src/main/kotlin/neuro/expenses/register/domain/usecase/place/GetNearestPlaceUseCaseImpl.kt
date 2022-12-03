package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase

class GetNearestPlaceUseCaseImpl(
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : GetNearestPlaceUseCase {
  override fun getNearestPlace(): Maybe<PlaceDto> {
    return getCurrentLocationUseCase.getCurrentLocation().flatMapMaybe {
      getNearestPlacesUseCase.getNearestPlaces(it, 1).filter { it.isNotEmpty() }
        .map { it.get(0) }
    }
  }
}