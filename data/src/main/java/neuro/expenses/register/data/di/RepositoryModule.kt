package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.GetCategoriesRepositoryImpl
import neuro.expenses.register.domain.repository.GetCategoriesRepository
import org.koin.dsl.module

val repositoryModule = module {
  single<GetCategoriesRepository> { GetCategoriesRepositoryImpl(get(), get()) }
}
