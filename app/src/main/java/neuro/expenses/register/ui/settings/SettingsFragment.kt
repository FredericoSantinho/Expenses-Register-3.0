package neuro.expenses.register.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.settings.SettingsViewModel
import neuro.expenses.register.viewmodel.settings.SettingsViewModel.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : PreferenceFragmentCompat() {

  private val settingsViewModel: SettingsViewModel by viewModel()

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.settings, rootKey)

    setOnClearDatabaseListener()
    setupViewModel()
  }

  private fun setOnClearDatabaseListener() {
    val preference: Preference = findPreference(getString(R.string.settings_key_clear_database))!!
    preference.setOnPreferenceClickListener {
      settingsViewModel.onClearDatabase()
      true
    }
  }

  private fun setupViewModel() {
    settingsViewModel.uiState.observe(this) {
      onUiState(it)
    }
  }

  private fun onUiState(uiState: UiState) {
    when (uiState) {
      UiState.Ready -> {}
      is UiState.ConfirmClearDatabase -> showClearDatabaseDialog()
    }
  }

  private fun showClearDatabaseDialog() {
    AlertDialog.Builder(requireContext())
      .setTitle(R.string.settings_clear_database_confirm_title)
      .setMessage(R.string.settings_clear_database_confirm_message)
      .setPositiveButton(android.R.string.ok) { _, _ ->
        settingsViewModel.onClearDatabaseConfirm()
      }
      .setNegativeButton(android.R.string.cancel) { _, _ ->
        settingsViewModel.onClearDatabaseRefuse()
      }
      .setIcon(android.R.drawable.ic_dialog_alert)
      .setOnKeyListener(object : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
          if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_UP) {
            settingsViewModel.onClearDatabaseRefuse()
          }
          return false
        }
      })
      .show()
  }
}