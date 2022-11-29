package neuro.expenses.register.entity.location

import neuro.expenses.register.entity.model.LatLng

interface CalculateDistanceService {
  fun calculateDistance(latLng1: LatLng, latLng2: LatLng): Double
}