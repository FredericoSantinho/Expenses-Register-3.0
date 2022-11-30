package neuro.expenses.register.presentation.di

import neuro.expenses.register.presentation.ui.home.mapper.HomeMapsUiEventMapper
import org.koin.dsl.module

val uiMapperModule = module {
  single { HomeMapsUiEventMapper() }
}