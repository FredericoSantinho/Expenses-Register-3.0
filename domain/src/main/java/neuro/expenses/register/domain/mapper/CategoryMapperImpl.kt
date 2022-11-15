package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.entity.Category

class CategoryMapperImpl : CategoryMapper {
  override fun map(category: Category): CategoryDto {
    return CategoryDto(category.id, category.name)
  }

  override fun map(categoryDto: CategoryDto): Category {
    return Category(categoryDto.id, categoryDto.name)
  }
}