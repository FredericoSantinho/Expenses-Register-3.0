package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.controller.CalculateBillTotal
import neuro.expenses.register.entity.controller.CalculateBillTotalImpl
import org.koin.dsl.module

val entityModule = module {
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
}