package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.domain.dto.LatLngDto

interface CalculateDistanceUseCase {
  fun calculateDistance(latLngDto1: LatLngDto, latLngDto2: LatLngDto): Double
}