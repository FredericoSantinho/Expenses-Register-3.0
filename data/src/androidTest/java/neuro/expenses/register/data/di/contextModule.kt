package neuro.expenses.register.data.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.koin.dsl.module

val contextModule = module {
  single<Context> { ApplicationProvider.getApplicationContext() }
}
