package neuro.expenses.register.di

import neuro.expenses.register.PrePopulateDatabase
import org.koin.dsl.module
import java.util.concurrent.Executors

val initModule = module {
  single { Executors.newSingleThreadExecutor() }
  single { PrePopulateDatabase(get()) }
}