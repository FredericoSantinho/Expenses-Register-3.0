package neuro.expenses.register.data.repository.category

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.domain.repository.category.DeleteCategoryRepository
import neuro.expenses.register.domain.usecase.category.DeleteCategoryError

class DeleteCategoryRepositoryImpl(private val categoryDao: CategoryDao) :
  DeleteCategoryRepository {
  override fun deleteCategory(categoryId: Long): Completable {
    return categoryDao.delete(categoryId).onErrorResumeNext {
      val error = if (it is SQLiteConstraintException) {
        DeleteCategoryError()
      } else {
        it
      }
      Completable.error(error)
    }
  }
}