package neuro.expenses.register.data.repository.category

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.domain.repository.category.GenerateCategoryIdRepository

class GenerateCategoryIdRepositoryImpl(private val categoryDao: CategoryDao) :
  GenerateCategoryIdRepository {
  override fun newId(): Single<Long> {
    return categoryDao.getLastCategoryId().defaultIfEmpty(0).map { it + 1 }
  }
}