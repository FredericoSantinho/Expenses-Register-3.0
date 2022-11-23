package neuro.expenses.register.di

import neuro.expenses.register.ui.home.mapper.HomeMapsUiEventMapper
import org.koin.dsl.module

val uiMapperModule = module {
  single { HomeMapsUiEventMapper() }
}