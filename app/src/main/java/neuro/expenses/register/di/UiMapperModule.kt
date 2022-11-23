package neuro.expenses.register.di

import neuro.expenses.register.ui.home.mapper.HomeMapsEventMapper
import org.koin.dsl.module

val uiMapperModule = module {
  single { HomeMapsEventMapper() }
}