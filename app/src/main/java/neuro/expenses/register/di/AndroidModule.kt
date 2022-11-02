package neuro.expenses.register.di

import neuro.expenses.register.common.shared.preferences.SecureSharedPreferencesCreator
import neuro.expenses.register.common.shared.preferences.SecureSharedPreferencesCreatorImpl
import neuro.expenses.register.first.run.FirstRun
import neuro.expenses.register.first.run.FirstRunImpl
import org.koin.dsl.module

private val SECURE_PREFERENCES_NAME = "expensesRegisterPreferences"

val androidModule = module {
  single<SecureSharedPreferencesCreator> { SecureSharedPreferencesCreatorImpl() }
  single { get<SecureSharedPreferencesCreator>().create(get(), SECURE_PREFERENCES_NAME) }
  single<FirstRun> { FirstRunImpl(get()) }
}