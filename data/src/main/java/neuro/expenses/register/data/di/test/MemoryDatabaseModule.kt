package neuro.expenses.register.data.di.test

import androidx.room.Room
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.converter.Converters
import org.koin.dsl.module

val memoryDatabaseModule = module {
  single {
    Room.inMemoryDatabaseBuilder(
      get(), ExpensesRegisterDatabase::class.java
    ).addTypeConverter(Converters()).build()
  }
}
