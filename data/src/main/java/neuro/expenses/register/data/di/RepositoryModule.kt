package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.*
import neuro.expenses.register.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
  single<GenerateProductIdRepository> { GenerateProductIdRepositoryImpl(get()) }
  single<ObserveCategoriesRepository> { ObserveCategoriesRepositoryImpl(get(), get()) }
  single<GetCategoryRepository> { GetCategoryRepositoryImpl(get(), get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get(), get()) }
  single<GetLastBillRepository> { GetLastBillRepositoryImpl(get(), get()) }
  single<SaveBillRepository> { SaveBillRepositoryImpl(get(), get(), get()) }
  single<SaveProductRepository> { SaveProductRepositoryImpl(get(), get()) }
  single<GetProductRepository> { GetProductRepositoryImpl(get(), get(), get()) }
  single<GeneratePlaceIdRepository> { GeneratePlaceIdRepositoryImpl(get()) }
  single<GetPlacesRepository> { GetPlacesRepositoryImpl(get(), get()) }
  single<ObservePlacesRepository> { ObservePlacesRepositoryImpl(get(), get()) }
  single<GetPlaceRepository> { GetPlaceRepositoryImpl(get(), get()) }
  single<SavePlaceRepository> { SavePlaceRepositoryImpl(get(), get()) }
  single<RemovePlaceProductRepository> { RemovePlaceProductRepositoryImpl(get()) }
}
