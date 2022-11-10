package neuro.expenses.register.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

val CURRENCY = "expensesRegisterPreferences"

val registerExpensesModule = module {
  single(named(CURRENCY)) { "€" }
}