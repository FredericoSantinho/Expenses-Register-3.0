package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.usecase.location.DummyGetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCaseImpl
import org.koin.dsl.module

val currentLocationModule = module {
  single<GetCurrentLocationUseCase> { GetCurrentLocationUseCaseImpl(get()) }
}

val dummyCurrentLocationModule = module {
  single<GetCurrentLocationUseCase> { DummyGetCurrentLocationUseCase() }
}