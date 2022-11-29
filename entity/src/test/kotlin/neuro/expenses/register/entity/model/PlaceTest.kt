package neuro.expenses.register.entity.model

import neuro.expenses.register.entity.mocks.latLngMock
import neuro.expenses.register.entity.mocks.placeProductsMock
import org.junit.jupiter.api.Assertions
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

    Assertions.assertEquals(place.id, id)
    Assertions.assertEquals(place.name, name)
    Assertions.assertEquals(place.placeProducts, placeProducts)
    Assertions.assertEquals(place.latLng, latLng)

    Assertions.assertEquals(place, placeEqual)
    Assertions.assertNotEquals(place, placeDifferent)
  }
}