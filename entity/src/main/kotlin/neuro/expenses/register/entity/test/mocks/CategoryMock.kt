package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.Category

fun categoryMock(name: String = "name"): Category {
  return Category(1, name, "iconUrl")
}
