package neuro.expenses.register.di

import org.koin.dsl.module
import java.util.concurrent.Executors

val initModule = module {
  single { Executors.newSingleThreadExecutor() }
}