package neuro.expenses.register

import android.app.Application
import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.dataServiceModule
import neuro.expenses.register.data.di.databaseModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.presentation.di.*
import neuro.expenses.register.viewmodel.application.ApplicationViewModel
import neuro.expenses.register.viewmodel.di.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ExpensesRegisterApplication : Application() {

  private val applicationViewModel: ApplicationViewModel by inject()

  override fun onCreate() {
    super.onCreate()

    startKoin {
      modules(
        registerExpensesModule,
        prePopulateModule,
        androidModule,
        schedulersModule,
        viewModelModule,
        viewModelMapperModule,
        useCaseModule,
        currentLocationModule,
        entityModule,
        entityImplModule,
        serviceModule,
        repositoryModule,
        dataServiceModule,
        databaseModule,
        uiMapperModule,
        daoModule,
        initModule
      )
      androidContext(this@ExpensesRegisterApplication)
    }

    applicationViewModel.onCreate()
  }
}