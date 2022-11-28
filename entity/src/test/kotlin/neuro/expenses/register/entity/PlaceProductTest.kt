package neuro.expenses.register.entity

import neuro.expenses.register.entity.mocks.categoryMock
import neuro.expenses.register.entity.mocks.productMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class PlaceProductTest {
  @Test
  fun test() {
    val id = 1L
    val product = productMock()
    val category = categoryMock()
    val price = 1.0

    val placeProduct = PlaceProduct(id, product, category, price)
    val placeProductEqual = PlaceProduct(id, product, category, price)
    val placeProductDifferent = PlaceProduct(id + 1, product, category, price)

    assertEquals(placeProduct.id, id)
    assertEquals(placeProduct.product, product)
    assertEquals(placeProduct.category, category)
    assertEquals(placeProduct.price, price)

    assertEquals(placeProduct, placeProductEqual)
    assertNotEquals(placeProduct, placeProductDifferent)
  }
}