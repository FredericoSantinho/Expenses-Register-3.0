package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.CategoryDto

fun categoryDtoMock(name: String = "name"): CategoryDto {
  return CategoryDto(1, name, "iconUrl")
}
