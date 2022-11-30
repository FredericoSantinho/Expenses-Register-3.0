package neuro.expenses.register.presentation.di

import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.dataServiceModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.data.di.test.memoryDatabaseModule
import neuro.expenses.register.viewmodel.di.*

val presentationTestModules = listOf(
  registerExpensesModule,
  prePopulateModule,
  androidModule,
  testSchedulersModule,
  viewModelModule,
  viewModelMapperModule,
  useCaseModule,
  entityModule,
  entityImplModule,
  serviceModule,
  repositoryModule,
  dataServiceModule,
  memoryDatabaseModule,
  uiMapperModule,
  daoModule,
  initModule,
  contextModule,
  populateModule
)
