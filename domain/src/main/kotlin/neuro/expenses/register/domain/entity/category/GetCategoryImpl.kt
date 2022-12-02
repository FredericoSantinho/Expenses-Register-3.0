package neuro.expenses.register.domain.entity.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.category.GetCategoryRepository
import neuro.expenses.register.entity.category.GetCategory
import neuro.expenses.register.entity.model.Category

class GetCategoryImpl(
  private val getCategoryRepository: GetCategoryRepository
) : GetCategory {
  override fun getCategory(nameLowercase: String): Maybe<Category> {
    return getCategoryRepository.getCategory(nameLowercase).map(CategoryDto::toEntity)
  }
}