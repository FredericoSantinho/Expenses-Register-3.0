package neuro.expenses.register.data.repository.category

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.category.toData
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.UpdateCategoryRepository
import neuro.expenses.register.domain.usecase.category.UpdateCategoryError

class UpdateCategoryRepositoryImpl(private val categoryDao: CategoryDao) :
  UpdateCategoryRepository {
  override fun updateCategory(categoryDto: CategoryDto): Completable {
    return categoryDao.update(categoryDto.toData()).onErrorResumeNext {
      val error = if (it is SQLiteConstraintException) {
        UpdateCategoryError()
      } else {
        it
      }
      Completable.error(error)
    }
  }
}