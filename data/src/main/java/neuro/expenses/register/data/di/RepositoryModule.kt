package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.*
import neuro.expenses.register.domain.repository.*
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
  single<RemovePlaceProductRepository> { RemovePlaceProductRepositoryImpl(get()) }
}
