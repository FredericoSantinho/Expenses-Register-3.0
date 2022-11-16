package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.*
import neuro.expenses.register.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
  single<GeneratePlaceProductIdRepository> { GeneratePlaceProductIdRepositoryImpl(get()) }
  single<ObserveCategoriesRepository> { ObserveCategoriesRepositoryImpl(get()) }
  single<GetCategoryRepository> { GetCategoryRepositoryImpl(get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get()) }
  single<GetLastBillRepository> { GetLastBillRepositoryImpl(get()) }
  single<SaveBillRepository> { SaveBillRepositoryImpl(get()) }
  single<SaveProductRepository> { SaveProductRepositoryImpl(get(), get()) }
  single<GetProductRepository> { GetProductRepositoryImpl(get(), get()) }
  single<GenerateProductIdRepository> { GenerateProductIdRepositoryImpl(get()) }
  single<GeneratePlaceIdRepository> { GeneratePlaceIdRepositoryImpl(get()) }
  single<GetPlacesRepository> { GetPlacesRepositoryImpl(get()) }
  single<ObservePlacesRepository> { ObservePlacesRepositoryImpl(get()) }
  single<GetPlaceRepository> { GetPlaceRepositoryImpl(get()) }
  single<SavePlaceRepository> { SavePlaceRepositoryImpl(get()) }
  single<RemovePlaceProductRepository> { RemovePlaceProductRepositoryImpl(get()) }
}
