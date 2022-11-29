package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.entity.model.Category

fun Category.toDomain() = CategoryDto(id, name, iconUrl)

fun CategoryDto.toEntity() = Category(id, name, iconUrl)