package neuro.expenses.register.di

import neuro.expenses.register.ui.home.mapper.HomeMapsEventMapper
import neuro.expenses.register.viewmodel.bill.mapper.BillModelMapper
import neuro.expenses.register.viewmodel.bill.mapper.BillModelMapperImpl
import neuro.expenses.register.viewmodel.di.CURRENCY
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val uiMapperModule = module {
  single<ProductCardModelMapper> { ProductCardModelMapperImpl(get(), get(named(CURRENCY))) }
  single<BillModelMapper> { BillModelMapperImpl(get(), get()) }
  single { HomeMapsEventMapper() }
}