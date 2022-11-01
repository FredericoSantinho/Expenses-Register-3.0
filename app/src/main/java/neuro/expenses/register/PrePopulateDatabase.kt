package neuro.expenses.register

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.model.RoomCategory

class PrePopulateDatabase(
  private val expensesRegisterDatabase: ExpensesRegisterDatabase
) {
  fun prePopulateDatabase(): Completable {
    return Single.just(expensesRegisterDatabase.categoryDao).flatMap { categoryDao ->
      categoryDao.insert(
        listOf(
          RoomCategory("aaa"),
          RoomCategory("abb"),
          RoomCategory("abc")
        )
      )
    }.ignoreElement()
  }
}
