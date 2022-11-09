package neuro.expenses.register.data.di

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import neuro.expenses.register.data.service.GetCurrentLocationServiceImpl
import neuro.expenses.register.domain.service.CalculateDistanceService
import neuro.expenses.register.domain.service.CalculateDistanceServiceImpl
import neuro.expenses.register.domain.service.GetCurrentLocationService
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val IO_SCHEDULER = "ioScheduler"

val serviceModule = module {
  single<Scheduler>(named(IO_SCHEDULER)) { Schedulers.io() }
  single<GetCurrentLocationService> {
    GetCurrentLocationServiceImpl(
      get(),
      get(named(IO_SCHEDULER))
    )
  }
  single<CalculateDistanceService> { CalculateDistanceServiceImpl() }
}
