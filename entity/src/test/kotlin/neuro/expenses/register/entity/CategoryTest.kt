package neuro.expenses.register.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
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

    assertEquals(category.id, id)
    assertEquals(category.name, name)
    assertEquals(category.iconUrl, iconUrl)

    assertEquals(category, categoryEqual)
    assertNotEquals(category, categoryDifferent)
  }
}