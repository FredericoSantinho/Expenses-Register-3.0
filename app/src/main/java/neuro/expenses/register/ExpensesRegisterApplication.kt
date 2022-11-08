package neuro.expenses.register

import android.app.Application
import com.exchangebot.common.schedulers.SchedulerProvider
import neuro.expenses.register.data.databaseModule
import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.databaseMapperModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.di.*
import neuro.expenses.register.domain.di.entityModule
import neuro.expenses.register.domain.di.useCaseModule
import neuro.expenses.register.first.run.FirstRun
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
        androidModule,
        schedulersModule,
        viewModelModule,
        useCaseModule,
        entityModule,
        repositoryModule,
        databaseModule,
        databaseMapperModule,
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