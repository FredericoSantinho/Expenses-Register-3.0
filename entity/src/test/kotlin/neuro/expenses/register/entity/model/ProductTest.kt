package neuro.expenses.register.entity.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ProductTest {
  @Test
  fun product() {
    val id = 1L
    val description = "description"
    val variableAmount = true
    val iconUrl = "iconUrl"

    val product = Product(id, description, variableAmount, iconUrl)
    val productEqual = Product(id, description, variableAmount, iconUrl)
    val productDifferent = Product(id + 1, description, variableAmount, iconUrl)

    Assertions.assertEquals(product.id, id)
    Assertions.assertEquals(product.description, description)
    Assertions.assertEquals(product.variableAmount, variableAmount)
    Assertions.assertEquals(product.iconUrl, iconUrl)

    Assertions.assertEquals(product, productEqual)
    Assertions.assertNotEquals(product, productDifferent)
  }
}