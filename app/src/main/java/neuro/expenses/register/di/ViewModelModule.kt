package neuro.expenses.register.di

import neuro.expenses.register.common.formatter.DecimalFormatter
import neuro.expenses.register.common.formatter.DecimalFormatterImpl
import neuro.expenses.register.common.formatter.NumberFormater
import neuro.expenses.register.common.formatter.NumberFormaterImpl
import neuro.expenses.register.ui.bills.BillsViewModel
import neuro.expenses.register.ui.common.bill.FeedLastBillViewModel
import neuro.expenses.register.ui.common.bill.FeedLastBillViewModelImpl
import neuro.expenses.register.ui.common.bill.mapper.DateTimeMapper
import neuro.expenses.register.ui.common.bill.mapper.DateTimeMapperImpl
import neuro.expenses.register.ui.common.bill.mapper.DoubleMapper
import neuro.expenses.register.ui.common.bill.mapper.DoubleMapperImpl
import neuro.expenses.register.ui.edit.EditViewModel
import neuro.expenses.register.ui.home.EditCategoryViewModel
import neuro.expenses.register.ui.home.EditPlaceViewModel
import neuro.expenses.register.ui.home.EditProductViewModel
import neuro.expenses.register.ui.home.view.model.BillViewModel
import neuro.expenses.register.ui.home.view.model.HomeViewModel
import neuro.expenses.register.ui.home.view.model.ProductsListViewModel
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapper
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { HomeViewModel(get()) }
  viewModel { ProductsListViewModel() }
  viewModel {
    ManualRegisterViewModel(
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get()
    )
  }
  single { BillViewModel() }
  single<FeedLastBillViewModel> {
    FeedLastBillViewModelImpl(
      get(),
      get(),
      get(),
      get(),
      get()
    )
  }
  single<DateTimeMapper> { DateTimeMapperImpl(get()) }
  single<NumberFormater> { NumberFormaterImpl() }
  single<DoubleMapper> { DoubleMapperImpl(get()) }
  single<DecimalFormatter> { DecimalFormatterImpl(2) }
  single<RegisterExpenseErrorMapper> { RegisterExpenseErrorMapperImpl() }
  viewModel { BillsViewModel() }
  viewModel { EditViewModel() }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}