package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.*
import neuro.expenses.register.data.repository.internal.SaveBillItemsProductsRepository
import neuro.expenses.register.data.repository.internal.SaveBillItemsProductsRepositoryImpl
import neuro.expenses.register.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
  single<ObserveCategoriesRepository> { ObserveCategoriesRepositoryImpl(get(), get()) }
  single<GetCategoryRepository> { GetCategoryRepositoryImpl(get(), get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get(), get()) }
  single<GetLastBillRepository> { GetLastBillRepositoryImpl(get(), get()) }
  single<SaveBillRepository> { SaveBillRepositoryImpl(get(), get(), get()) }
  single<SaveProductRepository> { SaveProductRepositoryImpl(get()) }
  single<GetPlacesRepository> { GetPlacesRepositoryImpl(get(), get()) }

  //  Internal
  single<SaveBillItemsProductsRepository> { SaveBillItemsProductsRepositoryImpl(get(), get()) }
}
