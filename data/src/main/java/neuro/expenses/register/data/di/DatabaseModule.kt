package neuro.expenses.register.data

import androidx.room.Room
import org.koin.dsl.module

private val DATABASE_NAME = "expenses"

val databaseModule = module {
  single {
    Room.databaseBuilder(
      get(),
      ExpensesRegisterDatabase::class.java,
      DATABASE_NAME
    ).build()
  }
}
