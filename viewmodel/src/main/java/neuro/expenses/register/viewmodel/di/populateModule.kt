package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.test.PopulatePlacesViewModel
import org.koin.dsl.module

val populateModule = module {
  single { PopulatePlacesViewModel(get()) }
}
