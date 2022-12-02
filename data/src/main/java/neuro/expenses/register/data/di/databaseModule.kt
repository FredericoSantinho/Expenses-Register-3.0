package neuro.expenses.register.data.di

import androidx.room.Room
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.converter.Converters
import org.koin.dsl.module

private val DATABASE_NAME = "expenses"

val databaseModule = module {
  single {
    Room.databaseBuilder(
      get(),
      ExpensesRegisterDatabase::class.java,
      DATABASE_NAME
    ).addTypeConverter(Converters()).build()
  }
}
