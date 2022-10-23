package neuro.expenses.register.di

import neuro.expenses.register.ui.detailed.DetailedViewModel
import neuro.expenses.register.ui.home.EditCategoryViewModel
import neuro.expenses.register.ui.home.EditPlaceViewModel
import neuro.expenses.register.ui.home.EditProductViewModel
import neuro.expenses.register.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { HomeViewModel() }
  viewModel { DetailedViewModel() }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}