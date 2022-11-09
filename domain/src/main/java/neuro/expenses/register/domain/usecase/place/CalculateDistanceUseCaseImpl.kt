package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.service.CalculateDistanceService

class CalculateDistanceUseCaseImpl(private val calculateDistanceService: CalculateDistanceService) :
  CalculateDistanceUseCase {
  override fun calculateDistance(latLngDto1: LatLngDto, latLngDto2: LatLngDto): Double {
    return calculateDistanceService.calculateDistance(latLngDto1, latLngDto2)
  }
}