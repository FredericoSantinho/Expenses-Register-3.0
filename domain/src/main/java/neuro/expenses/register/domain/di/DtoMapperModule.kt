package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.mapper.*
import org.koin.dsl.module

val mapperModule = module {
  single<ExpenseMapper> { ExpenseMapperImpl() }
  single<ProductMapper> { ProductMapperImpl() }
  single<BillMapper> { BillMapperImpl(get()) }
  single<BillItemMapper> { BillItemMapperImpl(get()) }
  single<PlaceMapper> { PlaceMapperImpl(get(), get()) }
  single<LatLngMapper> { LatLngMapperImpl() }
}