package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModelImpl
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapper
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapperImpl
import neuro.expenses.register.viewmodel.bills.BillsViewModel
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.NumberFormater
import neuro.expenses.register.viewmodel.common.formatter.NumberFormaterImpl
import neuro.expenses.register.viewmodel.edit.EditViewModel
import neuro.expenses.register.viewmodel.edit.category.EditCategoryViewModel
import neuro.expenses.register.viewmodel.edit.place.EditPlaceViewModel
import neuro.expenses.register.viewmodel.edit.product.EditProductViewModel
import neuro.expenses.register.viewmodel.home.HomeViewModel
import neuro.expenses.register.viewmodel.main.MainViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import neuro.expenses.register.viewmodel.manual.register.mapper.RegisterExpenseErrorMapper
import neuro.expenses.register.viewmodel.manual.register.mapper.RegisterExpenseErrorMapperImpl
import neuro.expenses.register.viewmodel.permissions.PermissionsViewModel
import neuro.expenses.register.viewmodel.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CURRENCY = "currency"

val viewModelModule = module {
  viewModel { PermissionsViewModel(get()) }
  viewModel { MainViewModel() }
  viewModel { SettingsViewModel() }
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
  single<DecimalFormatter> { DecimalFormatterImpl(2) }
  single<RegisterExpenseErrorMapper> { RegisterExpenseErrorMapperImpl() }
  viewModel { BillsViewModel() }
  viewModel { EditViewModel() }
  viewModel { EditProductViewModel() }
  viewModel { EditCategoryViewModel() }
  viewModel { EditPlaceViewModel() }
}