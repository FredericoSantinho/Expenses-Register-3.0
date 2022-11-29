package neuro.expenses.register.entity.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class LatLngTest {
  @Test
  fun test() {
    val latitude = 1.0
    val longitude = 2.0

    val latLng = LatLng(latitude, longitude)
    val latLngEqual = LatLng(latitude, longitude)
    val latLngDifferent = LatLng(latitude + 1, longitude)

    Assertions.assertEquals(latLng.latitude, latitude)
    Assertions.assertEquals(latLng.longitude, longitude)

    Assertions.assertEquals(latLng, latLngEqual)
    Assertions.assertNotEquals(latLng, latLngDifferent)
  }
}