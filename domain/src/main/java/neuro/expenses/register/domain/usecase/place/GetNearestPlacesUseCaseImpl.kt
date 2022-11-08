package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.GetPlacesRepository

class GetNearestPlacesUseCaseImpl(
  private val getPlacesRepository: GetPlacesRepository,
  private val getDistanceUseCase: GetDistanceUseCase
) : GetNearestPlacesUseCase {
  override fun getNearestPlaces(latLng: LatLngDto, limit: Int): Single<List<PlaceDto>> {
    return getPlacesRepository.getPlaces().map {
      it.sortedBy {
        getDistanceUseCase.getDistance(
          latLng,
          it.latLng
        )
      }.take(limit)
    }
  }
}