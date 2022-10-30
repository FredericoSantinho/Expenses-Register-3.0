package neuro.expenses.register.data.di

import neuro.expenses.register.data.ExpensesRegisterDatabase
import org.koin.dsl.module

val daoModule = module {
  single { get<ExpensesRegisterDatabase>().categoryDao }
}