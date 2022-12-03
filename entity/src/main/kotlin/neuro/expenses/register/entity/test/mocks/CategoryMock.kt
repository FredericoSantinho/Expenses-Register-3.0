package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.Category

fun categoryMock(id: Long = 1L, name: String = "name"): Category {
  return Category(id, name, "iconUrl")
}
