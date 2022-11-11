package neuro.expenses.register

import android.app.Application
import neuro.expenses.register.common.schedulers.SchedulerProvider
import neuro.expenses.register.data.di.*
import neuro.expenses.register.database.PrePopulateDatabase
import neuro.expenses.register.di.*
import neuro.expenses.register.domain.di.dtoMapperModule
import neuro.expenses.register.domain.di.entityModule
import neuro.expenses.register.domain.di.useCaseModule
import neuro.expenses.register.first.run.FirstRun
import neuro.expenses.register.viewmodel.di.viewmodelMapperModule
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
        viewmodelMapperModule,
        useCaseModule,
        dtoMapperModule,
        entityModule,
        repositoryModule,
        serviceModule,
        databaseModule,
        databaseMapperModule,
        uiMapperModule,
        factoryModule,
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