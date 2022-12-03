package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.CategoryDto

fun categoryDtoMock(id: Long = 1L, name: String = "name"): CategoryDto {
  return CategoryDto(id, name, "iconUrl")
}
