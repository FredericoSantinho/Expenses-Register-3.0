package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.ObservePlacesRepository
import neuro.expenses.register.entity.service.CalculateDistanceService

class ObserveNearestPlacesUseCaseImpl(
  private val observePlacesRepository: ObservePlacesRepository,
  private val calculateDistanceService: CalculateDistanceService
) : ObserveNearestPlacesUseCase {
  override fun observeNearestPlaces(latLngDto: LatLngDto, limit: Int): Observable<List<PlaceDto>> {
    return observePlacesRepository.observePlaces().map { it.toEntity() }
      .map {
        it.sortedBy {
          val latLng = latLngDto.toEntity()
          val placeLatLng = it.latLng

          calculateDistanceService.calculateDistance(latLng, placeLatLng)
        }.take(limit)
      }.map { it.toDomain() }
  }
}