package neuro.expenses.register.entity

import neuro.expenses.register.entity.mocks.latLngMock
import neuro.expenses.register.entity.mocks.placeProductsMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class PlaceTest {
  @Test
  fun test() {
    val id = 1L
    val name = "name"
    val placeProducts = placeProductsMock()
    val latLng = latLngMock()

    val place = Place(id, name, placeProducts, latLng)
    val placeEqual = Place(id, name, placeProducts, latLng)
    val placeDifferent = Place(id + 1, name, placeProducts, latLng)

    assertEquals(place.id, id)
    assertEquals(place.name, name)
    assertEquals(place.placeProducts, placeProducts)
    assertEquals(place.latLng, latLng)

    assertEquals(place, placeEqual)
    assertNotEquals(place, placeDifferent)
  }
}