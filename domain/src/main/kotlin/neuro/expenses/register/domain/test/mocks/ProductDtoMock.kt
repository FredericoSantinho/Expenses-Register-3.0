package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.ProductDto

fun productDtoMock(
  id: Long = 1L,
  description: String = "description $id",
  variableAmount: Boolean = false,
  iconUrl: String = "iconUrl"
): ProductDto {
  return ProductDto(id, description, variableAmount, iconUrl)
}
