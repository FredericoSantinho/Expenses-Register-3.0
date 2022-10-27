package neuro.expenses.register.di

import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.GetCategoriesUseCase
import neuro.expenses.register.domain.usecase.category.GetCategoriesUseCaseImpl
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCaseImpl
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCaseImpl
import neuro.expenses.register.ui.manual.register.mapper.BillItemVMMapper
import neuro.expenses.register.ui.manual.register.mapper.BillItemVMMapperImpl
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapper
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapperImpl
import org.koin.dsl.module

val useCaseModule = module {
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
  single<RegisterExpenseUseCase> { RegisterExpenseUseCaseImpl() }
  single<BillItemVMMapper> { BillItemVMMapperImpl() }
  single<RegisterExpenseErrorMapper> { RegisterExpenseErrorMapperImpl() }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl() }
}