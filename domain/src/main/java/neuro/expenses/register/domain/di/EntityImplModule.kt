package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.*
import neuro.expenses.register.entity.controller.bill.*
import neuro.expenses.register.entity.controller.bill.GenerateBillIdImpl
import neuro.expenses.register.entity.controller.bill.GenerateBillItemIdImpl
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.category.GetCategoryId
import neuro.expenses.register.entity.controller.place.GeneratePlaceId
import neuro.expenses.register.entity.controller.place.GetPlace
import neuro.expenses.register.entity.controller.place.SavePlace
import neuro.expenses.register.entity.controller.product.GeneratePlaceProductId
import neuro.expenses.register.entity.controller.product.GenerateProductId
import neuro.expenses.register.entity.controller.product.GetProduct
import neuro.expenses.register.entity.controller.product.SaveProduct
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProduct> { GetProductImpl(get()) }
  single<SaveProduct> { SaveProductImpl(get()) }
  single<GetCategoryId> { GetCategoryIdImpl(get()) }
  single<GetCategory> { GetCategoryImpl(get()) }
  single<GenerateProductId> { GenerateProductIdImpl(get()) }
  single<GeneratePlaceProductId> { GeneratePlaceProductIdImpl(get()) }
  single<GeneratePlaceId> { GeneratePlaceIdImpl(get()) }
  single<GenerateBillId> { GenerateBillIdImpl() }
  single<GenerateBillItemId> { GenerateBillItemIdImpl() }
  single<GetLastBill> { GetLastBillImpl(get()) }
  single<SaveBill> { SaveBillImpl(get()) }
  single<GetPlace> { GetPlaceImpl(get()) }
  single<SavePlace> { SavePlaceImpl(get()) }
}