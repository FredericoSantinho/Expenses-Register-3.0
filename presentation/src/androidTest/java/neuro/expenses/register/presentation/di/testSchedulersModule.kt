package neuro.expenses.register.presentation.di

import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.test.TestSchedulerProvider
import org.koin.dsl.module

val testSchedulersModule = module {
  single { TestSchedulerProvider() }
  single<SchedulerProvider> { get<TestSchedulerProvider>() }
}