package neuro.expenses.register.viewmodel.di

import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelMapperModule = module {
  single<SearchSuggestionModelMapper> {
    SearchSuggestionModelMapperImpl(
      get(),
      get(named(CURRENCY))
    )
  }
}