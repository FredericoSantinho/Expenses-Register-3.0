package neuro.expenses.register.di

import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactoryImpl
import org.koin.dsl.module

val factoryModule = module {
  single<ProductCardViewModelFactory> { ProductCardViewModelFactoryImpl(get(), get()) }
}