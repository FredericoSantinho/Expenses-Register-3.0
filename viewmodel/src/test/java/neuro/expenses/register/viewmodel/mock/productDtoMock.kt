package neuro.expenses.register.viewmodel.mock

import neuro.expenses.register.domain.dto.ProductDto

fun productMock(
  id: Long = 1L,
  description: String = "description $id",
  iconUrl: String = "iconUrl"
): ProductDto {
  return ProductDto(id, description, false, iconUrl)
}
