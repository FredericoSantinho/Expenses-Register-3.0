package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.RoomCategoriesMapper
import neuro.expenses.register.domain.repository.GetCategoryRepository

class GetCategoryRepositoryImpl(
  private val categoryDao: CategoryDao,
  private val roomCategoriesMapper: RoomCategoriesMapper
) : GetCategoryRepository {
  override fun getCategory(name: String): Maybe<String> {
    return categoryDao.getCategory(name.lowercase()).map { roomCategoriesMapper.map(it) }
  }
}