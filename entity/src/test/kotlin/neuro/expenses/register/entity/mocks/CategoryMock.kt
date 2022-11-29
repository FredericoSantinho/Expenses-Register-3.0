package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Category

fun categoryMock(name: String = "name"): Category {
  return Category(1, name, "iconUrl")
}
