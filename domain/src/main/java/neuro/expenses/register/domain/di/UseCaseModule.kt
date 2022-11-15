package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCaseImpl
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCaseImpl
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.IsValidCategoryUseCase
import neuro.expenses.register.domain.usecase.category.IsValidCategoryUseCaseImpl
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCaseImpl
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCaseImpl
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCaseImpl
import neuro.expenses.register.domain.usecase.place.*
import org.koin.dsl.module

val useCaseModule = module {
  single<RegisterExpenseUseCase> {
    RegisterExpenseUseCaseImpl(get(), get(), get())
  }
  single<ObserveLastBillUseCase> { ObserveLastBillUseCaseImpl(get(), get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl(get()) }
  single<IsValidCategoryUseCase> { IsValidCategoryUseCaseImpl(get()) }
  single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl(get(), get()) }
  single<GetNearestPlacesUseCase> { GetNearestPlacesUseCaseImpl(get()) }
  single<ObserveNearestPlacesUseCase> { ObserveNearestPlacesUseCaseImpl(get(), get(), get()) }
  single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
  single<SavePlaceUseCase> { SavePlaceUseCaseImpl(get()) }
  single<SaveExpensePlaceUseCase> {
    SaveExpensePlaceUseCaseImpl(
      get(),
      get(),
      get(),
      get(),
      get(),
      get()
    )
  }
  single<GetPlaceUseCase> { GetPlaceUseCaseImpl(get()) }
  single<UpdatePlaceProductUseCase> { UpdatePlaceProductUseCaseImpl(get(), get(), get(), get()) }
  single<AddPlaceProductUseCase> { AddPlaceProductUseCaseImpl(get(), get(), get(), get()) }
  single<RemovePlaceProductUseCase> { RemovePlaceProductUseCaseImpl(get()) }
}