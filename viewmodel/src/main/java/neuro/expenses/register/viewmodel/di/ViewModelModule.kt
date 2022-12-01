package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.application.ApplicationViewModel
import neuro.expenses.register.viewmodel.bill.*
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapper
import neuro.expenses.register.viewmodel.bill.mapper.DateTimeMapperImpl
import neuro.expenses.register.viewmodel.bills.BillsViewModel
import neuro.expenses.register.viewmodel.common.formatter.*
import neuro.expenses.register.viewmodel.edit.EditViewModel
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesViewModel
import neuro.expenses.register.viewmodel.edit.category.EditCategoryViewModel
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel
import neuro.expenses.register.viewmodel.home.HomeViewModel
import neuro.expenses.register.viewmodel.main.MainViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelStateImpl
import neuro.expenses.register.viewmodel.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CURRENCY = "currency"
const val DECIMALS = 2

val viewModelModule = module {
  single { ApplicationViewModel(get(), get(), get()) }
  single { MainViewModel(get(), get(), get(), get()) }
  single { AppBarViewModel() }
  single<ScaffoldViewModelState> { ScaffoldViewModelStateImpl() }
  viewModel { SettingsViewModel() }
  viewModel {
    HomeViewModel(
      get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()
    )
  }
  single { EditPlaceProductViewModel(get(), get(), get(), get()) }
  viewModel {
    ManualRegisterViewModel(
      get(), get(), get(), get(), get(), get(), get(), get()
    )
  }
  single<IBillCardViewModel> { BillCardViewModel() }
  single<FeedLastBillViewModel> { FeedLastBillViewModelImpl(get(), get(), get(), get()) }
  single<DateTimeMapper> { DateTimeMapperImpl(get()) }
  single<NumberFormater> { NumberFormaterImpl() }
  single<DecimalFormatter> { DecimalFormatterImpl(DECIMALS) }
  single<CurrencyFormatter> { CurrencyFormatterImpl(get(), get(named(CURRENCY))) }
  viewModel { BillsViewModel(get(), get(), get(), get(), get(), get(), get()) }
  single { BillDetailedViewModel(get(), get()) }
  viewModel { EditViewModel(get()) }
  viewModel { EditCategoriesViewModel(get(), get(), get()) }
  single { EditCategoryViewModel(get(), get(), get(), get()) }
}