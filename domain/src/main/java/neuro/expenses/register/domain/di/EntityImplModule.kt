package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.*
import neuro.expenses.register.entity.controller.bill.GenerateBillId
import neuro.expenses.register.entity.controller.bill.GenerateBillItemId
import neuro.expenses.register.entity.controller.bill.GetLastBill
import neuro.expenses.register.entity.controller.bill.SaveBill
import neuro.expenses.register.entity.controller.category.GenerateCategoryId
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.category.GetCategoryId
import neuro.expenses.register.entity.controller.location.GetCurrentLocation
import neuro.expenses.register.entity.controller.place.*
import neuro.expenses.register.entity.controller.product.*
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProduct> { GetProductImpl(get()) }
  single<GetPlaceProduct> { GetPlaceProductImpl(get()) }
  single<SaveProduct> { SaveProductImpl(get()) }
  single<SavePlaceProduct> { SavePlaceProductImpl(get()) }
  single<GetCategoryId> { GetCategoryIdImpl(get()) }
  single<GetCategory> { GetCategoryImpl(get()) }
  single<GenerateCategoryId> { GenerateCategoryIdImpl(get()) }
  single<GenerateProductId> { GenerateProductIdImpl(get()) }
  single<GeneratePlaceProductId> { GeneratePlaceProductIdImpl(get()) }
  single<GeneratePlaceId> { GeneratePlaceIdImpl(get()) }
  single<GenerateBillId> { GenerateBillIdImpl(get()) }
  single<GenerateBillItemId> { GenerateBillItemIdImpl(get()) }
  single<GetLastBill> { GetLastBillImpl(get()) }
  single<SaveBill> { SaveBillImpl(get()) }
  single<GetPlace> { GetPlaceImpl(get()) }
  single<SavePlace> { SavePlaceImpl(get()) }
  single<AddPlaceProduct> { AddPlaceProductImpl(get()) }
  single<RemovePlaceProduct> { RemovePlaceProductImpl(get()) }
  single<GetCurrentLocation> { GetCurrentLocationImpl(get()) }
}