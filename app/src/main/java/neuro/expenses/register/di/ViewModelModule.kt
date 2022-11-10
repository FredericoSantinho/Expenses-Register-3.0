package neuro.expenses.register.di

import neuro.expenses.register.common.formatter.*
import neuro.expenses.register.ui.bills.BillsViewModel
import neuro.expenses.register.ui.edit.EditViewModel
import neuro.expenses.register.ui.home.EditCategoryViewModel
import neuro.expenses.register.ui.home.EditPlaceViewModel
import neuro.expenses.register.ui.home.EditProductViewModel
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapper
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapperImpl
import neuro.expenses.register.ui.splash.SplashViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModelImpl
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapper
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapperImpl
import neuro.expenses.register.viewmodel.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { SplashViewModel(get()) }
  viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
  viewModel {
    ManualRegisterViewModel(
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get(named(CURRENCY))
    )
  }
  single { BillViewModel() }
  single<FeedLastBillViewModel> { FeedLastBillViewModelImpl(get(), get(), get(), get()) }
  single<DateTimeMapper> { DateTimeMapperImpl(get()) }
  single<NumberFormater> { NumberFormaterImpl() }
  single<DoubleFormatter> { DoubleFormatterImpl(get()) }
  single<DecimalFormatter> { DecimalFormatterImpl(2) }
  single<RegisterExpenseErrorMapper> { RegisterExpenseErrorMapperImpl() }
  viewModel { BillsViewModel() }
  viewModel { EditViewModel() }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}