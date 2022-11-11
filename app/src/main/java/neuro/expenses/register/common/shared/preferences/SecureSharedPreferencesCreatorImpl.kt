package neuro.expenses.register.common.shared.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecureSharedPreferencesCreatorImpl(
  private val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
) : SecureSharedPreferencesCreator {

  override fun create(context: Context, fileName: String): SharedPreferences {
    return EncryptedSharedPreferences.create(
      fileName,
      masterKeyAlias,
      context,
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
  }
}