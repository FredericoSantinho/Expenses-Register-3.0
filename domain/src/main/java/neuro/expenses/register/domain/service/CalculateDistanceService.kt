package neuro.expenses.register.domain.service

import neuro.expenses.register.domain.dto.LatLngDto

interface CalculateDistanceService {
  fun calculateDistance(latLngDto1: LatLngDto, latLngDto2: LatLngDto): Double
}