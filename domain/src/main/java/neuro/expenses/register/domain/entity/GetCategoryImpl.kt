package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.category.GetCategoryRepository
import neuro.expenses.register.entity.Category
import neuro.expenses.register.entity.controller.category.GetCategory

class GetCategoryImpl(
  private val getCategoryRepository: GetCategoryRepository
) : GetCategory {
  override fun getCategory(nameLowercase: String): Maybe<Category> {
    return getCategoryRepository.getCategory(nameLowercase).map(CategoryDto::toEntity)
  }
}