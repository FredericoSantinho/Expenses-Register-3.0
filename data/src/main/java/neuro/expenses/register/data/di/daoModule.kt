package neuro.expenses.register.data.di

import neuro.expenses.register.data.ExpensesRegisterDatabase
import org.koin.dsl.module

val daoModule = module {
  single { get<ExpensesRegisterDatabase>().productDao }
  single { get<ExpensesRegisterDatabase>().placeProductDao }
  single { get<ExpensesRegisterDatabase>().categoryDao }
  single { get<ExpensesRegisterDatabase>().billDao }
  single { get<ExpensesRegisterDatabase>().placeDao }
}
