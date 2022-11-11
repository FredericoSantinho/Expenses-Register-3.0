package neuro.expenses.register.entity.service

import neuro.expenses.register.entity.LatLng
import org.junit.Test
import kotlin.test.assertEquals

internal class CalculateDistanceServiceImplTest {

  @Test
  fun calculateDistance() {
    val calculateDistanceService = CalculateDistanceServiceImpl()
    val latLng1 = LatLng(5.3, 5.3)
    val latLng2 = LatLng(13.1, 11.1)
    val expectedDistanceMeters = 1075575.920

    assertEquals(
      expectedDistanceMeters,
      calculateDistanceService.calculateDistance(latLng1, latLng2),
      0.001
    )
    assertEquals(
      expectedDistanceMeters,
      calculateDistanceService.calculateDistance(latLng2, latLng1), 0.001
    )
    assertEquals(
      0.0,
      calculateDistanceService.calculateDistance(latLng1, latLng1), 0.0
    )
  }
}