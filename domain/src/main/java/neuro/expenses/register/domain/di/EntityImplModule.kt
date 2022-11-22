package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.*
import neuro.expenses.register.entity.controller.bill.*
import neuro.expenses.register.entity.controller.bill.GenerateBillIdImpl
import neuro.expenses.register.entity.controller.bill.GenerateBillItemIdImpl
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
  single<GenerateProductId> { GenerateProductIdImpl(get()) }
  single<GeneratePlaceProductId> { GeneratePlaceProductIdImpl(get()) }
  single<GeneratePlaceId> { GeneratePlaceIdImpl(get()) }
  single<GenerateBillId> { GenerateBillIdImpl() }
  single<GenerateBillItemId> { GenerateBillItemIdImpl() }
  single<GetLastBill> { GetLastBillImpl(get()) }
  single<SaveBill> { SaveBillImpl(get()) }
  single<GetPlace> { GetPlaceImpl(get()) }
  single<SavePlace> { SavePlaceImpl(get()) }
  single<AddPlaceProduct> { AddPlaceProductImpl(get()) }
  single<RemovePlaceProduct> { RemovePlaceProductImpl(get()) }
  single<GetCurrentLocation> { GetCurrentLocationImpl(get()) }
}