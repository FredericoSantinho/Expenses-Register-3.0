package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.bill.mapper.*
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapperImpl
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapperImpl
import org.koin.dsl.module

val viewModelMapperModule = module {
  single<SearchSuggestionModelMapper> {
    SearchSuggestionModelMapperImpl(get())
  }
  single<PlaceProductCardModelMapper> { PlaceProductCardModelMapperImpl(get()) }
  single<BillModelMapper> { BillModelMapperImpl(get(), get()) }
  single<BillViewModelMapper> { BillViewModelMapperImpl(get()) }
  single { BillItemModelMapper(get()) }
}