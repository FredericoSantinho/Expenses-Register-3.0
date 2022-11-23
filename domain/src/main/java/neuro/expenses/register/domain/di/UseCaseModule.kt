package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.bill.*
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCaseImpl
import neuro.expenses.register.domain.usecase.category.SaveCategoryUseCase
import neuro.expenses.register.domain.usecase.category.SaveCategoryUseCaseImpl
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCaseImpl
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCaseImpl
import neuro.expenses.register.domain.usecase.place.*
import neuro.expenses.register.domain.usecase.product.GetOrCreatePlaceProductUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreatePlaceProductUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
  single<RegisterExpenseUseCase> { RegisterExpenseUseCaseImpl(get()) }
  single<GetBillUseCase> { GetBillUseCaseImpl(get()) }
  single<ObserveLastBillUseCase> { ObserveLastBillUseCaseImpl(get(), get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl(get()) }
  single<ObserveBillsUseCase> { ObserveBillsUseCaseImpl(get()) }
  single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
  single<SaveCategoryUseCase> { SaveCategoryUseCaseImpl(get()) }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl(get(), get()) }
  single<GetNearestPlacesUseCase> { GetNearestPlacesUseCaseImpl(get()) }
  single<ObserveNearestPlacesUseCase> { ObserveNearestPlacesUseCaseImpl(get(), get()) }
  single<SortPlaceProducts> { SortPlaceProductsImpl() }
  single<SortBills> { SortBillsImpl() }
  single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
  single<SavePlaceUseCase> { SavePlaceUseCaseImpl(get()) }
  single<GetPlaceUseCase> { GetPlaceUseCaseImpl(get()) }
  single<GetOrCreatePlaceProductUseCase> { GetOrCreatePlaceProductUseCaseImpl(get()) }
  single<UpdatePlaceProductUseCase> { UpdatePlaceProductUseCaseImpl(get()) }
  single<AddPlaceProductUseCase> { AddPlaceProductUseCaseImpl(get(), get()) }
  single<RemovePlaceProductUseCase> { RemovePlaceProductUseCaseImpl(get()) }
}