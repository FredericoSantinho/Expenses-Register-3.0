package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.GetPlacesRepository

class GetNearestPlacesUseCaseImpl(
  private val getPlacesRepository: GetPlacesRepository,
  private val calculateDistanceUseCase: CalculateDistanceUseCase
) : GetNearestPlacesUseCase {
  override fun getNearestPlaces(latLng: LatLngDto, limit: Int): Single<List<PlaceDto>> {
    return getPlacesRepository.getPlaces().map {
      it.sortedBy {
        calculateDistanceUseCase.calculateDistance(
          latLng,
          it.latLngDto
        )
      }.take(limit)
    }
  }
}