package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.mapper.BillItemDtoMapper
import neuro.expenses.register.domain.mapper.BillItemDtoMapperImpl
import neuro.expenses.register.domain.mapper.ProductDtoMapper
import neuro.expenses.register.domain.mapper.ProductDtoMapperImpl
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCaseImpl
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
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCaseImpl
import neuro.expenses.register.domain.usecase.register.validator.BillItemValidator
import neuro.expenses.register.domain.usecase.register.validator.BillItemValidatorImpl
import org.koin.dsl.module

val useCaseModule = module {
  single<RegisterExpenseUseCase> { RegisterExpenseUseCaseImpl(get(), get(), get(), get()) }
  single<GetLastBillUseCase> { GetLastBillUseCaseImpl() }
  single<SaveBillUseCase> { SaveBillUseCaseImpl() }
  single<BillItemDtoMapper> { BillItemDtoMapperImpl(get()) }
  single<ProductDtoMapper> { ProductDtoMapperImpl() }
  single<BillItemValidator> { BillItemValidatorImpl(get()) }
  single<IsValidCategory> { IsValidCategoryImpl() }
  single<GetCalendarUseCase> { GetCalendarUseCaseImpl() }
  single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
  single<GetNearestPlaceUseCase> { GetNearestPlaceUseCaseImpl() }
}