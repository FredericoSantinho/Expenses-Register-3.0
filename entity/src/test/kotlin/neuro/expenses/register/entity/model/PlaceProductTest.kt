package neuro.expenses.register.entity.model

import neuro.expenses.register.entity.mocks.categoryMock
import neuro.expenses.register.entity.mocks.productMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PlaceProductTest {
  @Test
  fun placeProduct() {
    val id = 1L
    val product = productMock()
    val category = categoryMock()
    val price = 1.0

    val placeProduct = PlaceProduct(id, product, category, price)
    val placeProductEqual = PlaceProduct(id, product, category, price)
    val placeProductDifferent = PlaceProduct(id + 1, product, category, price)

    Assertions.assertEquals(placeProduct.id, id)
    Assertions.assertEquals(placeProduct.product, product)
    Assertions.assertEquals(placeProduct.category, category)
    Assertions.assertEquals(placeProduct.price, price)

    Assertions.assertEquals(placeProduct, placeProductEqual)
    Assertions.assertNotEquals(placeProduct, placeProductDifferent)
  }
}