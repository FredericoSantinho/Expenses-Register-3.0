package neuro.expenses.register

import android.Manifest
import android.content.pm.PackageManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import neuro.expenses.register.common.android.activity.BaseActivity
import neuro.expenses.register.databinding.ActivityEntryBinding
import org.koin.core.component.inject

class EntryActivity : BaseActivity() {

  private lateinit var binding: ActivityEntryBinding
  val entryViewModel: EntryViewModel by inject()

  override fun getView(): View {
    binding = ActivityEntryBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun setupViewModel() {
    entryViewModel.uiEvent.observe(this) { onUiEvent(it) }
  }

  private fun onUiEvent(uiEvent: UiEvent) {
    when (uiEvent) {
      is UiEvent.CheckPermissions -> checkPermissions()
      is UiEvent.RequestPermissions -> showPermissionsDialog()
      is UiEvent.NavigateToMainActivity -> navigateToMainActivity()
    }
  }

  private fun checkPermissions() {
    if (ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      entryViewModel.onPermissionsNotGranted()
    } else {
      entryViewModel.onPermissionsGranted()
    }
  }

  private fun showPermissionsDialog() {
    AlertDialog.Builder(this)
      .setTitle(R.string.permissions_title)
      .setMessage(R.string.permissions_text)
      .setPositiveButton(android.R.string.ok) { _, _ ->
        requestPermissions()
      }
      .setNegativeButton(android.R.string.cancel, { _, _ ->
        finish()
      })
      .setIcon(android.R.drawable.ic_dialog_alert)
      .show()
  }

  private fun requestPermissions() {
    ActivityCompat.requestPermissions(
      this,
      arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
      ),
      PERMISSIONS_REQUEST_CODE
    )
  }

  private fun navigateToMainActivity() {
    navigateTo(EntryFragmentDirections.actionNavigationEntryToNavigationMain())
    finish()
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    if (requestCode == PERMISSIONS_REQUEST_CODE) {
      checkPermissions()
    }
  }

  companion object {
    private const val PERMISSIONS_REQUEST_CODE = 1
  }
}