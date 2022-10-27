package neuro.expenses.register.data.di

import neuro.expenses.register.data.mapper.CategoriesMapper
import neuro.expenses.register.data.mapper.CategoriesMapperImpl
import org.koin.dsl.module

val databaseMapperModule = module {
  single<CategoriesMapper> { CategoriesMapperImpl() }
}
