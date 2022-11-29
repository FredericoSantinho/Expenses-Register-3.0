package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.entity.bill.GenerateBillIdImpl
import neuro.expenses.register.domain.entity.bill.GenerateBillItemIdImpl
import neuro.expenses.register.domain.entity.bill.GetLastBillImpl
import neuro.expenses.register.domain.entity.bill.SaveBillImpl
import neuro.expenses.register.domain.entity.category.GenerateCategoryIdImpl
import neuro.expenses.register.domain.entity.category.GetCategoryImpl
import neuro.expenses.register.domain.entity.location.GetCurrentLocationImpl
import neuro.expenses.register.domain.entity.place.AddPlaceProductImpl
import neuro.expenses.register.domain.entity.place.GeneratePlaceIdImpl
import neuro.expenses.register.domain.entity.place.GetPlaceImpl
import neuro.expenses.register.domain.entity.place.SavePlaceImpl
import neuro.expenses.register.domain.entity.placeproduct.GeneratePlaceProductIdImpl
import neuro.expenses.register.domain.entity.placeproduct.GetPlaceProductImpl
import neuro.expenses.register.domain.entity.placeproduct.RemovePlaceProductImpl
import neuro.expenses.register.domain.entity.placeproduct.SavePlaceProductImpl
import neuro.expenses.register.domain.entity.product.GenerateProductIdImpl
import neuro.expenses.register.domain.entity.product.GetProductImpl
import neuro.expenses.register.domain.entity.product.SaveProductImpl
import neuro.expenses.register.entity.bill.GenerateBillId
import neuro.expenses.register.entity.bill.GenerateBillItemId
import neuro.expenses.register.entity.bill.GetLastBill
import neuro.expenses.register.entity.bill.SaveBill
import neuro.expenses.register.entity.category.GenerateCategoryId
import neuro.expenses.register.entity.category.GetCategory
import neuro.expenses.register.entity.location.GetCurrentLocation
import neuro.expenses.register.entity.place.*
import neuro.expenses.register.entity.placeproduct.GeneratePlaceProductId
import neuro.expenses.register.entity.placeproduct.GetPlaceProduct
import neuro.expenses.register.entity.placeproduct.SavePlaceProduct
import neuro.expenses.register.entity.product.GenerateProductId
import neuro.expenses.register.entity.product.GetProduct
import neuro.expenses.register.entity.product.SaveProduct
import org.koin.dsl.module

val entityImplModule = module {
  single<GetProduct> { GetProductImpl(get()) }
  single<GetPlaceProduct> { GetPlaceProductImpl(get()) }
  single<SaveProduct> { SaveProductImpl(get()) }
  single<SavePlaceProduct> { SavePlaceProductImpl(get()) }
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