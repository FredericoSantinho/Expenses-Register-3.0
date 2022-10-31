package neuro.expenses.register.domain.entity.di

import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.entity.controller.CalculateBillTotalImpl
import org.koin.dsl.module

val entityModule = module {
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
}