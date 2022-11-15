package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.category.GetCategoryIdImpl
import neuro.expenses.register.domain.usecase.product.GetProductIdImpl
import neuro.expenses.register.entity.controller.GetCategoryId
import neuro.expenses.register.entity.controller.GetProductId
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProductId> { GetProductIdImpl(get()) }
  single<GetCategoryId> { GetCategoryIdImpl(get()) }
}