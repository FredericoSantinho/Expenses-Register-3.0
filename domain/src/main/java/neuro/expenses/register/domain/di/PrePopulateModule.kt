package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.populate.*
import org.koin.dsl.module

val prePopulateModule = module {
  single<PrePopulate> { PrePopulateImpl(get(), get()) }
  single<PopulatePlaces> { PopulatePlacesImpl(get(), get(), get()) }
  single<PopulateExpenses> { PopulateExpensesImpl(get()) }
}