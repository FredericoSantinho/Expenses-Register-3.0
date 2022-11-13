package neuro.expenses.register.domain.di

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
import neuro.expenses.register.domain.usecase.place.*
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidatorImpl
import neuro.expenses.register.entity.controller.CalculateBillTotal
import neuro.expenses.register.entity.controller.CalculateBillTotalImpl
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
  single<ObserveLastBillUseCase> { ObserveLastBillUseCaseImpl(get(), get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl(get()) }
  single<SaveBillUseCase> { SaveBillUseCaseImpl(get(), get()) }
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
  single<ExpenseValidator> { ExpenseValidatorImpl(get()) }
  single<IsValidCategoryUseCase> { IsValidCategoryUseCaseImpl(get()) }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl(get(), get()) }
  single<GetNearestPlacesUseCase> { GetNearestPlacesUseCaseImpl(get()) }
  single<ObserveNearestPlacesUseCase> { ObserveNearestPlacesUseCaseImpl(get(), get(), get()) }
  single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
  single<SavePlaceUseCase> { SavePlaceUseCaseImpl(get()) }
  single<SaveExpensePlaceUseCase> { SaveExpensePlaceUseCaseImpl(get(), get(), get(), get(), get()) }
  single<GetPlaceUseCase> { GetPlaceUseCaseImpl(get()) }
  single<UpdatePlaceProductUseCase> { UpdatePlaceProductUseCaseImpl(get(), get(), get(), get()) }
  single<AddPlaceProductUseCase> { AddPlaceProductUseCaseImpl(get(), get(), get(), get()) }
  single<RemovePlaceProductUseCase> { RemovePlaceProductUseCaseImpl(get()) }
}