package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.LatLngMapper
import neuro.expenses.register.domain.repository.ObservePlacesRepository
import neuro.expenses.register.entity.service.CalculateDistanceService

class ObserveNearestPlacesUseCaseImpl(
  private val observePlacesRepository: ObservePlacesRepository,
  private val latLngMapper: LatLngMapper,
  private val calculateDistanceService: CalculateDistanceService
) : ObserveNearestPlacesUseCase {
  override fun observeNearestPlaces(latLngDto: LatLngDto, limit: Int): Observable<List<PlaceDto>> {
    return observePlacesRepository.observePlaces().map {
      it.sortedBy {
        val latLng = latLngMapper.map(latLngDto)
        val placeLatLng = latLngMapper.map(it.latLngDto)

        calculateDistanceService.calculateDistance(latLng, placeLatLng)
      }.take(limit)
    }
  }
}