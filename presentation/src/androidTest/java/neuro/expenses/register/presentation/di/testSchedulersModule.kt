package neuro.expenses.register.presentation.di

import neuro.expenses.register.presentation.test.TestSchedulerProvider
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import org.koin.dsl.module

val testSchedulersModule = module {
  single { TestSchedulerProvider() }
  single<SchedulerProvider> { get<TestSchedulerProvider>() }
}