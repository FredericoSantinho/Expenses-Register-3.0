package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.bill.GetLastBillRepositoryImpl
import neuro.expenses.register.data.repository.bill.ObserveBillsRepositoryImpl
import neuro.expenses.register.data.repository.bill.ObserveLastBillRepositoryImpl
import neuro.expenses.register.data.repository.bill.SaveBillRepositoryImpl
import neuro.expenses.register.data.repository.category.GetCategoryRepositoryImpl
import neuro.expenses.register.data.repository.category.ObserveCategoriesRepositoryImpl
import neuro.expenses.register.data.repository.category.SaveCategoryRepositoryImpl
import neuro.expenses.register.data.repository.place.*
import neuro.expenses.register.data.repository.product.*
import neuro.expenses.register.domain.repository.bill.GetLastBillRepository
import neuro.expenses.register.domain.repository.bill.ObserveBillsRepository
import neuro.expenses.register.domain.repository.bill.ObserveLastBillRepository
import neuro.expenses.register.domain.repository.bill.SaveBillRepository
import neuro.expenses.register.domain.repository.category.GetCategoryRepository
import neuro.expenses.register.domain.repository.category.ObserveCategoriesRepository
import neuro.expenses.register.domain.repository.category.SaveCategoryRepository
import neuro.expenses.register.domain.repository.place.*
import neuro.expenses.register.domain.repository.product.*
import org.koin.dsl.module

val repositoryModule = module {
  single<GeneratePlaceProductIdRepository> { GeneratePlaceProductIdRepositoryImpl(get()) }
  single<ObserveCategoriesRepository> { ObserveCategoriesRepositoryImpl(get()) }
  single<GetCategoryRepository> { GetCategoryRepositoryImpl(get()) }
  single<SaveCategoryRepository> { SaveCategoryRepositoryImpl(get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get()) }
  single<ObserveBillsRepository> { ObserveBillsRepositoryImpl(get()) }
  single<GetLastBillRepository> { GetLastBillRepositoryImpl(get()) }
  single<SaveBillRepository> { SaveBillRepositoryImpl(get()) }
  single<SaveProductRepository> { SaveProductRepositoryImpl(get()) }
  single<SavePlaceProductRepository> { SavePlaceProductRepositoryImpl(get()) }
  single<GetProductRepository> { GetProductRepositoryImpl(get()) }
  single<GetPlaceProductRepository> { GetPlaceProductRepositoryImpl(get(), get()) }
  single<GenerateProductIdRepository> { GenerateProductIdRepositoryImpl(get()) }
  single<GeneratePlaceIdRepository> { GeneratePlaceIdRepositoryImpl(get()) }
  single<SavePlaceRepository> { SavePlaceRepositoryImpl(get()) }
  single<GetPlacesRepository> { GetPlacesRepositoryImpl(get()) }
  single<ObservePlacesRepository> { ObservePlacesRepositoryImpl(get()) }
  single<GetPlaceRepository> { GetPlaceRepositoryImpl(get()) }
  single<AddPlaceProductRepository> { AddPlaceProductRepositoryImpl(get()) }
  single<RemovePlaceProductRepository> { RemovePlaceProductRepositoryImpl(get()) }
}
