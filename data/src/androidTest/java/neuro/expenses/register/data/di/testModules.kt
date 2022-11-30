package neuro.expenses.register.data.di

import neuro.expenses.register.data.di.test.contextModule
import neuro.expenses.register.data.di.test.memoryDatabaseModule
import neuro.expenses.register.domain.di.*

val dataTestModules = listOf(
  prePopulateModule,
  useCaseModule,
  entityModule,
  entityImplModule,
  serviceModule,
  repositoryModule,
  dataServiceModule,
  memoryDatabaseModule,
  daoModule,
  contextModule
)
