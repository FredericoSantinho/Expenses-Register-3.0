package neuro.expenses.register.entity.location

import neuro.expenses.register.entity.model.LatLng

interface CalculateDistanceService {
  /**
   * Calculate distance between two locations.
   *
   * @param latLng1 first location.
   * @param latLng2 second location.
   * @return distance between two locations.
   */
  fun calculateDistance(latLng1: LatLng, latLng2: LatLng): Double
}