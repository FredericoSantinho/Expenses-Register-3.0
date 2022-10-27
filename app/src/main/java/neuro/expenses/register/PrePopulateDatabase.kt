package neuro.expenses.register

import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.model.RoomCategory

class PrePopulateDatabase(private val expensesRegisterDatabase: ExpensesRegisterDatabase) {
  fun prePopulateDatabase() {
    val categoryDao = expensesRegisterDatabase.categoryDao
    categoryDao.insert(RoomCategory("aaa"))
    categoryDao.insert(RoomCategory("abb"))
    categoryDao.insert(RoomCategory("abc"))
  }
}