package neuro.expenses.register

import android.app.Application
import neuro.expenses.register.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class ExpensesRegisterApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      modules(viewModelModule)
    }
  }
}