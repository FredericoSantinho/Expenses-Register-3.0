package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.entity.controller.CalculateBillTotalImpl
import neuro.expenses.register.domain.mapper.*
import neuro.expenses.register.domain.usecase.bill.*
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.IsValidCategoryUseCase
import neuro.expenses.register.domain.usecase.category.IsValidCategoryUseCaseImpl
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCaseImpl
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCaseImpl
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCaseImpl
import neuro.expenses.register.domain.usecase.place.GetDistanceUseCase
import neuro.expenses.register.domain.usecase.place.GetDistanceUseCaseImpl
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCaseImpl
import neuro.expenses.register.domain.usecase.product.GetOrCreateProductUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreateProductUseCaseImpl
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidatorImpl
import org.koin.dsl.module

val useCaseModule = module {
  single<RegisterExpenseUseCase> {
    RegisterExpenseUseCaseImpl(
      get(),
      get(),
      get(),
      get(),
      get(),
      get(),
      get()
    )
  }
  single<ObserveLastBillUseCase> { ObserveLastBillUseCaseImpl(get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl(get()) }
  single<SaveBillUseCase> { SaveBillUseCaseImpl(get(), get()) }
  single<BillItemMapper> { BillItemMapperImpl(get()) }
  single<BillMapper> { BillMapperImpl(get()) }
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
  single<ProductMapper> { ProductMapperImpl() }
  single<ExpenseValidator> { ExpenseValidatorImpl(get()) }
  single<IsValidCategoryUseCase> { IsValidCategoryUseCaseImpl(get()) }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl(get(), get()) }
  single<GetOrCreateProductUseCase> { GetOrCreateProductUseCaseImpl() }
  single<ExpenseMapper> { ExpenseMapperImpl() }
  single<GetNearestPlacesUseCase> { GetNearestPlacesUseCaseImpl(get(), get()) }
  single<GetDistanceUseCase> { GetDistanceUseCaseImpl() }
  single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
}