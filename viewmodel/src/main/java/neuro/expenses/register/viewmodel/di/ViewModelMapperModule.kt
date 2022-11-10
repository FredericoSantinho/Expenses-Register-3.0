package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.home.mapper.LatLngModelMapper
import neuro.expenses.register.viewmodel.home.mapper.LatLngModelMapperImpl
import org.koin.dsl.module

val viewmodelMapperModule = module {
  single<LatLngModelMapper> { LatLngModelMapperImpl() }
}
