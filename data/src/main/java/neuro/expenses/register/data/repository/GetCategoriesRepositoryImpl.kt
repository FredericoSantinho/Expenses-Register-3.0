package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.RoomCategoriesMapper
import neuro.expenses.register.domain.repository.GetCategoriesRepository

class GetCategoriesRepositoryImpl(
  private val categoryDao: CategoryDao,
  private val roomCategoriesMapper: RoomCategoriesMapper
) : GetCategoriesRepository {
  override fun getCategories(): Observable<List<String>> {
    return categoryDao.observeCategories().map { roomCategoriesMapper.map(it) }
  }
}