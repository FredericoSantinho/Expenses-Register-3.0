package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.service.CalculateDistanceService
import neuro.expenses.register.entity.service.CalculateDistanceServiceImpl
import org.koin.dsl.module

val serviceModule = module {
  single<CalculateDistanceService> { CalculateDistanceServiceImpl() }
}