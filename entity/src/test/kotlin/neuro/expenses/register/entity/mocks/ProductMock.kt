package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Product

fun productMock(
  id: Long = 1L,
  description: String = "desc$id",
  iconUrl: String = "iconUrl"
): Product {
  return Product(id, description, false, iconUrl)
}
