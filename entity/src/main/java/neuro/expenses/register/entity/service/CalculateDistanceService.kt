package neuro.expenses.register.entity.service

import neuro.expenses.register.entity.LatLng

interface CalculateDistanceService {
  fun calculateDistance(latLng1: LatLng, latLng2: LatLng): Double
}