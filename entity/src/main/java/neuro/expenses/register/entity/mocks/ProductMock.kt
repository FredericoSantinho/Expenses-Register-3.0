package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Product

fun productMock(id: Long = 1L): Product {
  return Product(id, "desc$id", false, "iconUrl")
}
