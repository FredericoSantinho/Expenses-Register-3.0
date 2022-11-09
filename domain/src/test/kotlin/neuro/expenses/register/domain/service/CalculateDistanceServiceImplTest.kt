package neuro.expenses.register.domain.service

import neuro.expenses.register.domain.dto.LatLngDto
import org.junit.Test
import kotlin.test.assertEquals

internal class CalculateDistanceServiceImplTest {

  @Test
  fun calculateDistance() {
    val calculateDistanceService = CalculateDistanceServiceImpl()
    val latLngDto1 = LatLngDto(5.3, 5.3)
    val latLngDto2 = LatLngDto(13.1, 11.1)
    val expectedDistanceMeters = 1075575.920

    assertEquals(
      expectedDistanceMeters,
      calculateDistanceService.calculateDistance(latLngDto1, latLngDto2),
      0.001
    )
    assertEquals(
      expectedDistanceMeters,
      calculateDistanceService.calculateDistance(latLngDto2, latLngDto1), 0.001
    )
    assertEquals(
      0.0,
      calculateDistanceService.calculateDistance(latLngDto1, latLngDto1), 0.0
    )
  }
}