package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.mapper.ProductMapper
import neuro.expenses.register.entity.mapper.ProductMapperImpl
import org.koin.dsl.module

val entityMapperModule = module {
  single<ProductMapper> { ProductMapperImpl() }
}