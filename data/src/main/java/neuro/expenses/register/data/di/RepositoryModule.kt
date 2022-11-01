package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.GetCategoriesRepositoryImpl
import neuro.expenses.register.data.repository.ObserveLastBillRepositoryImpl
import neuro.expenses.register.domain.repository.GetCategoriesRepository
import neuro.expenses.register.domain.repository.ObserveLastBillRepository
import org.koin.dsl.module

val repositoryModule = module {
  single<GetCategoriesRepository> { GetCategoriesRepositoryImpl(get(), get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get(), get()) }
}
