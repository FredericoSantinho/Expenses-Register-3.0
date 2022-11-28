package neuro.expenses.register.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class LatLngTest {
  @Test
  fun test() {
    val latitude = 1.0
    val longitude = 2.0

    val latLng = LatLng(latitude, longitude)
    val latLngEqual = LatLng(latitude, longitude)
    val latLngDifferent = LatLng(latitude + 1, longitude)

    assertEquals(latLng.latitude, latitude)
    assertEquals(latLng.longitude, longitude)

    assertEquals(latLng, latLngEqual)
    assertNotEquals(latLng, latLngDifferent)
  }
}