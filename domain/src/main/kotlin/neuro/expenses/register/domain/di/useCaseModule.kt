package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.bill.*
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.*
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.place.*
import neuro.expenses.register.domain.usecase.placeproduct.GetOrCreatePlaceProductUseCase
import neuro.expenses.register.domain.usecase.placeproduct.GetOrCreatePlaceProductUseCaseImpl
import org.koin.dsl.module
import java.util.*

val useCaseModule = module {
  single<RegisterExpenseUseCase> { RegisterExpenseUseCaseImpl(get()) }
  single<GetBillUseCase> { GetBillUseCaseImpl(get()) }
  single<ObserveLastBillUseCase> { ObserveLastBillUseCaseImpl(get(), get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl(get()) }
  single<ObserveBillsUseCase> { ObserveBillsUseCaseImpl(get()) }
  single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
  single<CreateCategoryUseCase> { CreateCategoryUseCaseImpl(get(), get()) }
  single<SaveCategoryUseCase> { SaveCategoryUseCaseImpl(get()) }
  single<DeleteCategoryUseCase> { DeleteCategoryUseCaseImpl(get()) }
  single<UpdateCategoryUseCase> { UpdateCategoryUseCaseImpl(get()) }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl(Calendar.getInstance()) }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl(get(), get()) }
  single<GetNearestPlacesUseCase> { GetNearestPlacesUseCaseImpl(get()) }
  single<ObserveNearestPlacesUseCase> { ObserveNearestPlacesUseCaseImpl(get(), get()) }
  single<SortPlaceProducts> { SortPlaceProductsImpl() }
  single<SortBills> { SortBillsImpl() }
  single<SavePlaceUseCase> { SavePlaceUseCaseImpl(get()) }
  single<GetPlaceUseCase> { GetPlaceUseCaseImpl(get()) }
  single<GetOrCreatePlaceProductUseCase> { GetOrCreatePlaceProductUseCaseImpl(get()) }
  single<UpdatePlaceProductUseCase> { UpdatePlaceProductUseCaseImpl(get()) }
  single<AddPlaceProductUseCase> { AddPlaceProductUseCaseImpl(get(), get()) }
  single<RemovePlaceProductUseCase> { RemovePlaceProductUseCaseImpl(get()) }
}