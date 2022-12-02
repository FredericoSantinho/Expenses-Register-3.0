package neuro.expenses.register.presentation.di

import neuro.expenses.register.viewmodel.di.CURRENCY
import org.koin.core.qualifier.named
import org.koin.dsl.module

val registerExpensesModule = module {
  single(named(CURRENCY)) { "€" }
}