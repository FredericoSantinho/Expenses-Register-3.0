package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto

class GetNearestPlacesUseCaseImpl(
  private val observeNearestPlacesUseCase: ObserveNearestPlacesUseCase
) : GetNearestPlacesUseCase {
  override fun getNearestPlaces(latLngDto: LatLngDto, limit: Int): Single<List<PlaceDto>> {
    return observeNearestPlacesUseCase.observeNearestPlaces(latLngDto, limit).firstOrError()
  }
}