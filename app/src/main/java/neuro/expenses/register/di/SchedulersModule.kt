package neuro.expenses.register.di

import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProviderImpl
import org.koin.dsl.module

val schedulersModule = module {
  single<SchedulerProvider> { SchedulerProviderImpl() }
}