package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.location.CalculateDistanceService
import neuro.expenses.register.entity.location.CalculateDistanceServiceImpl
import org.koin.dsl.module

val serviceModule = module {
  single<CalculateDistanceService> { CalculateDistanceServiceImpl() }
}