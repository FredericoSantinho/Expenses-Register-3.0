package neuro.expenses.register.domain.di

import neuro.expenses.register.domain.mapper.*
import org.koin.dsl.module

val mapperModule = module {
  single<CategoryMapper> { CategoryMapperImpl() }
  single<ExpenseMapper> { ExpenseMapperImpl() }
  single<RegisterExpenseErrorMapper> { RegisterExpenseErrorMapperImpl() }
  single<ProductMapper> { ProductMapperImpl(get()) }
  single<BillMapper> { BillMapperImpl(get()) }
  single<BillItemMapper> { BillItemMapperImpl(get()) }
  single<PlaceMapper> { PlaceMapperImpl(get(), get()) }
  single<LatLngMapper> { LatLngMapperImpl() }
}