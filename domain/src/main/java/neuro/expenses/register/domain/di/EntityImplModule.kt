package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.GenerateProductIdImpl
import neuro.expenses.register.domain.entity.GetCategoryIdImpl
import neuro.expenses.register.domain.entity.GetProductIdImpl
import neuro.expenses.register.entity.controller.GenerateProductId
import neuro.expenses.register.entity.controller.GetCategoryId
import neuro.expenses.register.entity.controller.GetProductId
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProductId> { GetProductIdImpl(get()) }
  single<GetCategoryId> { GetCategoryIdImpl(get()) }
  single<GenerateProductId> { GenerateProductIdImpl(get()) }
}