package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.converter.ExpenseConverter
import neuro.expenses.register.entity.converter.ExpenseConverterImpl
import org.koin.dsl.module

val entityConverterModule = module {
  single<ExpenseConverter> { ExpenseConverterImpl(get()) }
}