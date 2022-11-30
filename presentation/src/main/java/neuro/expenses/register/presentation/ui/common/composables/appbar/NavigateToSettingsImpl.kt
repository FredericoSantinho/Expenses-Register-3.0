package neuro.expenses.register.presentation.ui.common.composables.appbar

import android.content.Context
import android.content.Intent
import neuro.expenses.register.presentation.ui.settings.SettingsActivity

class NavigateToSettingsImpl : NavigateToSettings {
  override fun navigateToSettings(context: Context) {
    val i = Intent(context, SettingsActivity::class.java)
    context.startActivity(i)
  }
}