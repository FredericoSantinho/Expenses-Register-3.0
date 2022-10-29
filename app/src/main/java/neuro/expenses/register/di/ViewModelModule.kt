package neuro.expenses.register.di

import neuro.expenses.register.ui.bills.BillsViewModel
import neuro.expenses.register.ui.home.*
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { HomeViewModel(get()) }
  viewModel { ProductsListViewModel() }
  viewModel { ManualRegisterViewModel(get(), get(), get(), get(), get(), get()) }
  viewModel { BillsViewModel() }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}