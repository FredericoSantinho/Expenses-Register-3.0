package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.bill.mapper.BillModelMapper
import neuro.expenses.register.viewmodel.bill.mapper.BillModelMapperImpl
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapperImpl
import neuro.expenses.register.viewmodel.edit.bill.mapper.BillItemModelMapper
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapperImpl
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelMapperModule = module {
  single<SearchSuggestionModelMapper> {
    SearchSuggestionModelMapperImpl(
      get(), get(named(CURRENCY))
    )
  }
  single<ProductCardModelMapper> { ProductCardModelMapperImpl(get(), get(named(CURRENCY))) }
  single<BillModelMapper> { BillModelMapperImpl(get(), get(), get(named(CURRENCY))) }
  single<BillViewModelMapper> { BillViewModelMapperImpl(get()) }
  single { BillItemModelMapper(get(), get(named(CURRENCY))) }
}