package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.entity.Category

interface CategoryMapper {
  fun map(category: Category): CategoryDto
  fun map(categoryDto: CategoryDto): Category
}