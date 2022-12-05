package neuro.expenses.register.presentation.di

import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.dataServiceModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.data.di.test.memoryDatabaseModule
import neuro.expenses.register.viewmodel.di.*
import org.koin.core.module.Module

val presentationTestModules = listOf(
  registerExpensesModule,
  prePopulateModule,
  androidModule,
  viewModelModule,
  viewModelMapperModule,
  useCaseModule,
  entityModule,
  entityImplModule,
  serviceModule,
  repositoryModule,
  dataServiceModule,
  uiMapperModule,
  daoModule,
  initModule,
  contextModule,
  memoryDatabaseModule,
  testSchedulersModule,
  populateModule,
  currentLocationModule
)

fun presentationTestModulesDummyLocation(): List<Module> {
  val list = mutableListOf<Module>()
  list.addAll(presentationTestModules)
  list.add(dummyCurrentLocationModule)
  return list.toList()
}