package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.Product

fun productMock(
  id: Long = 1L,
  description: String = "description $id",
  variableAmount: Boolean = false,
  iconUrl: String = "iconUrl"
): Product {
  return Product(id, description, variableAmount, iconUrl)
}
