package neuro.expenses.register

import android.app.Application
import neuro.expenses.register.data.databaseModule
import neuro.expenses.register.data.di.daoModule
import neuro.expenses.register.data.di.databaseMapperModule
import neuro.expenses.register.data.di.repositoryModule
import neuro.expenses.register.di.initModule
import neuro.expenses.register.di.useCaseModule
import neuro.expenses.register.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.concurrent.ExecutorService

class ExpensesRegisterApplication : Application() {

  private val executorService: ExecutorService by inject()
  private val prePopulateDatabase: PrePopulateDatabase by inject()

  override fun onCreate() {
    super.onCreate()

    startKoin {
      modules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        databaseModule,
        databaseMapperModule,
        daoModule,
        initModule
      )
      androidContext(this@ExpensesRegisterApplication)
    }

    executorService.execute { prePopulateDatabase.prePopulateDatabase() }
  }
}