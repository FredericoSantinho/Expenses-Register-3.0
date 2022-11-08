package neuro.expenses.register.di

import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.ui.common.mapper.LatLngMapperImpl
import neuro.expenses.register.ui.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.ui.home.factory.ProductCardViewModelFactoryImpl
import org.koin.dsl.module

val uiMapperModule = module {
  single<LatLngMapper> { LatLngMapperImpl() }
  single<ProductCardViewModelFactory> { ProductCardViewModelFactoryImpl(get(), get(), get()) }
}