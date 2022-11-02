package neuro.expenses.register.common.shared.preferences

import android.content.Context
import android.content.SharedPreferences

interface SecureSharedPreferencesCreator {
  fun create(context: Context, fileName: String): SharedPreferences
}