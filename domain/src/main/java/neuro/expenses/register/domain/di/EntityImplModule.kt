package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.*
import neuro.expenses.register.entity.controller.bill.GetLastBill
import neuro.expenses.register.entity.controller.bill.SaveBill
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.category.GetCategoryId
import neuro.expenses.register.entity.controller.product.GenerateProductId
import neuro.expenses.register.entity.controller.product.GetProductId
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProductId> { GetProductIdImpl(get()) }
  single<GetCategoryId> { GetCategoryIdImpl(get()) }
  single<GetCategory> { GetCategoryImpl(get(), get()) }
  single<GenerateProductId> { GenerateProductIdImpl(get()) }
  single<GetLastBill> { GetLastBillImpl(get(), get()) }
  single<SaveBill> { SaveBillImpl(get(), get()) }
}