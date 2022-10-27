package neuro.expenses.register.data

import androidx.room.Database
import androidx.room.RoomDatabase
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.model.RoomCategory

@Database(entities = [RoomCategory::class], version = 1)
abstract class ExpensesRegisterDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
}
