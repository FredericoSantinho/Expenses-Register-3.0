package neuro.expenses.register.entity.location

import neuro.expenses.register.entity.model.LatLng

class CalculateDistanceServiceImpl : CalculateDistanceService {
  override fun calculateDistance(latLng1: LatLng, latLng2: LatLng): Double {
    return distance(
      latLng1.latitude,
      latLng2.latitude,
      latLng1.longitude,
      latLng2.longitude
    )
  }

  /**
   * Calculate distance between two points in latitude and longitude taking into account height
   * difference. If you are not interested in height difference pass 0.0. Uses Haversine method as
   * its base.
   *
   * @param lat1 start latitude.
   * @param lat2 end latitude.
   * @param lon1 start longitude.
   * @param lon2 end latitude.
   * @param el1 start elevation.
   * @param el2 end elevation.
   *
   * @returns Distance in Meters
   */
  private fun distance(
    lat1: Double,
    lat2: Double,
    lon1: Double,
    lon2: Double,
    el1: Double,
    el2: Double
  ): Double {
    val r = 6371 // Radius of the earth

    val latDistance = Math.toRadians(lat2 - lat1)
    val lonDistance = Math.toRadians(lon2 - lon1)
    val a =
      Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(
        Math.toRadians(lat2)
      ) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    var distance = r * c * 1000 // convert to meters

    val height = el1 - el2

    distance = Math.pow(distance, 2.0) + Math.pow(height, 2.0)

    return Math.sqrt(distance)
  }

  private fun distance(lat1: Double, lat2: Double, lon1: Double, lon2: Double): Double {
    return distance(lat1, lat2, lon1, lon2, 0.0, 0.0)
  }
}