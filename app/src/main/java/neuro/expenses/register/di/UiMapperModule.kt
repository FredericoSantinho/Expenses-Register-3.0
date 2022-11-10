package neuro.expenses.register.di

import neuro.expenses.register.ui.common.bill.mapper.BillModelMapper
import neuro.expenses.register.ui.common.bill.mapper.BillModelMapperImpl
import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.ui.common.mapper.LatLngMapperImpl
import neuro.expenses.register.ui.home.mapper.ProductCardModelMapper
import neuro.expenses.register.ui.home.mapper.ProductCardModelMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val uiMapperModule = module {
  single<LatLngMapper> { LatLngMapperImpl() }
  single<ProductCardModelMapper> { ProductCardModelMapperImpl(get(), get(named(CURRENCY))) }
  single<BillModelMapper> { BillModelMapperImpl(get(), get()) }
}