package neuro.expenses.register.di

import com.exchangebot.common.schedulers.SchedulerProvider
import com.exchangebot.common.schedulers.SchedulerProviderImpl
import org.koin.dsl.module

val schedulersModule = module {
  single<SchedulerProvider> { SchedulerProviderImpl() }
}