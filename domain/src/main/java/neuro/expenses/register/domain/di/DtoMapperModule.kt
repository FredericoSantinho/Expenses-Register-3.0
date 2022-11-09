package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.mapper.PlaceMapperImpl
import org.koin.dsl.module

val dtoMapperModule = module {
  single<PlaceMapper> { PlaceMapperImpl() }
}