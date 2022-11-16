package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.entity.Category

fun Category.toDomain() = CategoryDto(id, name)

fun CategoryDto.toEntity() = Category(id, name)