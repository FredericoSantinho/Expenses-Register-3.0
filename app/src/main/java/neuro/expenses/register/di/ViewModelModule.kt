package neuro.expenses.register.di

import neuro.expenses.register.ui.home.EditCategoryViewModel
import neuro.expenses.register.ui.home.EditPlaceViewModel
import neuro.expenses.register.ui.home.EditProductViewModel
import neuro.expenses.register.ui.home.HomeViewModel
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { HomeViewModel() }
  viewModel { ManualRegisterViewModel(get(), get(), get(), get(), get(), get()) }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}