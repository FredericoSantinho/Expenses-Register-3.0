package neuro.expenses.register

import android.app.Application
import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.dataServiceModule
import neuro.expenses.register.data.di.databaseModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.database.PrePopulateDatabase
import neuro.expenses.register.di.*
import neuro.expenses.register.first.run.FirstRun
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.di.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ExpensesRegisterApplication : Application() {

  private val schedulerProvider: SchedulerProvider by inject()
  private val prePopulateDatabase: PrePopulateDatabase by inject()
  private val firstRun: FirstRun by inject()

  override fun onCreate() {
    super.onCreate()

    startKoin {
      modules(
        registerExpensesModule,
        androidModule,
        schedulersModule,
        viewModelModule,
        useCaseModule,
        entityModule,
        entityImplModule,
        controllerModule,
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

    if (firstRun.isFirstRun()) {
      prePopulateDatabase.prePopulateDatabase().subscribeOn(schedulerProvider.io()).subscribe()
    }

    firstRun.setFirstRun(false)
  }
}