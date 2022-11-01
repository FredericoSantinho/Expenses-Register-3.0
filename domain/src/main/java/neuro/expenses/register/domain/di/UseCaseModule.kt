package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.entity.controller.CalculateBillTotalImpl
import neuro.expenses.register.domain.mapper.*
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCaseImpl
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCaseImpl
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCaseImpl
import neuro.expenses.register.domain.usecase.category.GetCategoriesUseCase
import neuro.expenses.register.domain.usecase.category.GetCategoriesUseCaseImpl
import neuro.expenses.register.domain.usecase.category.IsValidCategory
import neuro.expenses.register.domain.usecase.category.IsValidCategoryImpl
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCaseImpl
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
  single<SaveBillUseCase> { SaveBillUseCaseImpl() }
  single<BillItemMapper> { BillItemMapperImpl(get()) }
  single<BillMapper> { BillMapperImpl(get()) }
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
  single<ProductMapper> { ProductMapperImpl() }
  single<ExpenseValidator> { ExpenseValidatorImpl(get()) }
  single<IsValidCategory> { IsValidCategoryImpl() }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl() }
  single<GetOrCreateProductUseCase> { GetOrCreateProductUseCaseImpl() }
  single<ExpenseMapper> { ExpenseMapperImpl() }
}