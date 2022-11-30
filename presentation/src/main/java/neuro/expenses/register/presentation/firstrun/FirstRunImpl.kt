package neuro.expenses.register.presentation.firstrun

import android.content.SharedPreferences
import neuro.expenses.register.viewmodel.application.FirstRun

class FirstRunImpl(private val sharedPreferences: SharedPreferences) : FirstRun {
  override fun isFirstRun(): Boolean {
    return sharedPreferences.getBoolean(FIRST_RUN, true)
  }

  override fun setFirstRun(firstRun: Boolean) {
    sharedPreferences.edit()
      .putBoolean(FIRST_RUN, firstRun)
      .apply()
  }

  companion object {
    private val FIRST_RUN = "firstRun"
  }
}