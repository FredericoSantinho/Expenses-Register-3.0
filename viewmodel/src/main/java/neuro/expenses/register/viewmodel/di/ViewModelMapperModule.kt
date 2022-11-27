package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.bill.mapper.*
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapperImpl
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapperImpl
import org.koin.dsl.module

val viewModelMapperModule = module {
  single<SearchSuggestionModelMapper> {
    SearchSuggestionModelMapperImpl(get())
  }
  single<ProductCardModelMapper> { ProductCardModelMapperImpl(get()) }
  single<BillModelMapper> { BillModelMapperImpl(get(), get()) }
  single<BillViewModelMapper> { BillViewModelMapperImpl(get()) }
  single { BillItemModelMapper(get()) }
}