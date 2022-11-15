package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.CategoryMapper
import neuro.expenses.register.domain.repository.GetCategoryRepository
import neuro.expenses.register.entity.Category
import neuro.expenses.register.entity.controller.category.GetCategory

class GetCategoryImpl(
  private val getCategoryRepository: GetCategoryRepository,
  private val categoryMapper: CategoryMapper
) :
  GetCategory {
  override fun getCategory(nameLowercase: String): Maybe<Category> {
    return getCategoryRepository.getCategory(nameLowercase).map { categoryMapper.map(it) }
  }
}