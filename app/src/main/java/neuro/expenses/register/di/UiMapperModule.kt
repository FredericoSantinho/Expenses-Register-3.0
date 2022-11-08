package neuro.expenses.register.di

import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.ui.common.mapper.LatLngMapperImpl
import org.koin.dsl.module

val uiMapperModule = module {
  single<LatLngMapper> { LatLngMapperImpl() }
}