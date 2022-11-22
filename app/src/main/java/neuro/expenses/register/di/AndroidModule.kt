package neuro.expenses.register.di

import neuro.expenses.register.common.shared.preferences.SecureSharedPreferencesCreator
import neuro.expenses.register.common.shared.preferences.SecureSharedPreferencesCreatorImpl
import neuro.expenses.register.first.run.FirstRunImpl
import neuro.expenses.register.ui.common.composables.appbar.NavigateToSettings
import neuro.expenses.register.ui.common.composables.appbar.NavigateToSettingsImpl
import neuro.expenses.register.viewmodel.application.FirstRun
import org.koin.dsl.module

private const val SECURE_PREFERENCES_NAME = "expensesRegisterPreferences"

val androidModule = module {
  single<SecureSharedPreferencesCreator> { SecureSharedPreferencesCreatorImpl() }
  single { get<SecureSharedPreferencesCreator>().create(get(), SECURE_PREFERENCES_NAME) }
  single<FirstRun> { FirstRunImpl(get()) }
  single<NavigateToSettings> { NavigateToSettingsImpl() }
}