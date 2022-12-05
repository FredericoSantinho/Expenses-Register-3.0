package neuro.expenses.register.domain.repository.category

import neuro.expenses.register.domain.usecase.category.CreateCategoryError
import neuro.expenses.register.domain.usecase.category.SaveCategoryError
import neuro.expenses.register.domain.usecase.category.UpdateCategoryError
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CategoryExceptionsTest {
  @Test
  fun test() {
    assertEquals(CreateCategoryError(), CreateCategoryError())
    assertEquals(SaveCategoryError(), SaveCategoryError())
    assertEquals(UpdateCategoryError(), UpdateCategoryError())

    val map = mutableMapOf<Any, Boolean>()
    map.put(CreateCategoryError(), true)
    map.put(SaveCategoryError(), true)
    map.put(UpdateCategoryError(), true)

    assertEquals(3, map.values.size)

    map.put(CreateCategoryError(), true)
    map.put(SaveCategoryError(), true)
    map.put(UpdateCategoryError(), true)

    assertEquals(3, map.values.size)
  }
}