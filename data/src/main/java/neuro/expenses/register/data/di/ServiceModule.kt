package neuro.expenses.register.data.di

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import neuro.expenses.register.data.service.GetCurrentLocationServiceImpl
import neuro.expenses.register.domain.service.GetCurrentLocationService
import org.koin.dsl.module

val serviceModule = module {
  single<GetCurrentLocationService> { GetCurrentLocationServiceImpl(get(), get()) }
  single<Scheduler> { Schedulers.io() }
}
