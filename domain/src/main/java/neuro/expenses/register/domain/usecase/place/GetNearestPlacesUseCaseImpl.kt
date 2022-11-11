package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.LatLngMapper
import neuro.expenses.register.domain.repository.GetPlacesRepository
import neuro.expenses.register.entity.service.CalculateDistanceService

class GetNearestPlacesUseCaseImpl(
  private val getPlacesRepository: GetPlacesRepository,
  private val calculateDistanceService: CalculateDistanceService,
  private val latLngMapper: LatLngMapper
) : GetNearestPlacesUseCase {
  override fun getNearestPlaces(latLngDto: LatLngDto, limit: Int): Single<List<PlaceDto>> {
    return getPlacesRepository.getPlaces().map {
      it.sortedBy {
        val latLng = latLngMapper.map(latLngDto)
        val placeLatLng = latLngMapper.map(it.latLngDto)

        calculateDistanceService.calculateDistance(latLng, placeLatLng)
      }.take(limit)
    }
  }
}