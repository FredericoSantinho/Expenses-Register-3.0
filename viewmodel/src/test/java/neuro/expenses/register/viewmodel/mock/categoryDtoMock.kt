package neuro.expenses.register.viewmodel.mock

import neuro.expenses.register.domain.dto.CategoryDto

fun categoryDtoMock(id: Long = 0, name: String = "name $id"): CategoryDto {
  return CategoryDto(id, name, "iconUrl")
}
