package neuro.expenses.register.entity.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CategoryTest {
  @Test
  fun test() {
    val id = 1L
    val name = "name"
    val iconUrl = "iconUrl"

    val category = Category(id, name, iconUrl)
    val categoryEqual = Category(id, name, iconUrl)
    val categoryDifferent = Category(id + 1, name, iconUrl)

    Assertions.assertEquals(category.id, id)
    Assertions.assertEquals(category.name, name)
    Assertions.assertEquals(category.iconUrl, iconUrl)

    Assertions.assertEquals(category, categoryEqual)
    Assertions.assertNotEquals(category, categoryDifferent)
  }
}