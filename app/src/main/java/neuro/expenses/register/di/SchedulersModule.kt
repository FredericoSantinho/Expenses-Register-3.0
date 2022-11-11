package neuro.expenses.register.di

import neuro.expenses.register.common.schedulers.SchedulerProvider
import neuro.expenses.register.common.schedulers.SchedulerProviderImpl
import org.koin.dsl.module

val schedulersModule = module {
  single<SchedulerProvider> { SchedulerProviderImpl() }
}