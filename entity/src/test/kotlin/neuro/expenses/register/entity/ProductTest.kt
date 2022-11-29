package neuro.expenses.register.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class ProductTest {
  @Test
  fun test() {
    val id = 1L
    val description = "description"
    val variableAmount = true
    val iconUrl = "iconUrl"

    val product = Product(id, description, variableAmount, iconUrl)
    val productEqual = Product(id, description, variableAmount, iconUrl)
    val productDifferent = Product(id + 1, description, variableAmount, iconUrl)

    assertEquals(product.id, id)
    assertEquals(product.description, description)
    assertEquals(product.variableAmount, variableAmount)
    assertEquals(product.iconUrl, iconUrl)

    assertEquals(product, productEqual)
    assertNotEquals(product, productDifferent)
  }
}