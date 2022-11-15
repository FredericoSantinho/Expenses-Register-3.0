package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.controller.bill.CalculateBillTotal
import neuro.expenses.register.entity.controller.bill.CalculateBillTotalImpl
import org.koin.dsl.module

val entityModule = module {
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
}