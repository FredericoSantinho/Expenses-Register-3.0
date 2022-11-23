package neuro.expenses.register.ui.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import neuro.expenses.register.R
import neuro.expenses.register.common.activity.BaseActivity
import neuro.expenses.register.databinding.ActivityPermissionsBinding
import neuro.expenses.register.viewmodel.permissions.PermissionsUiEvent.UiEvent
import neuro.expenses.register.viewmodel.permissions.PermissionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PermissionsActivity : BaseActivity<ActivityPermissionsBinding>() {

  val permissionsViewModel: PermissionsViewModel by viewModel()

  override fun getViewBinding(): ActivityPermissionsBinding {
    return ActivityPermissionsBinding.inflate(layoutInflater)
  }

  override fun setupViewModel() {
    permissionsViewModel.uiEvent.observe(this) { onUiEvent(it) }
  }

  private fun onUiEvent(uiEvent: UiEvent?) {
    permissionsViewModel.eventConsumed()
    when (uiEvent) {
      is UiEvent.CheckPermissions -> checkPermissions()
      is UiEvent.RequestPermissions -> showPermissionsDialog()
      is UiEvent.NavigateToMainActivity -> navigateToMainActivity()
      null -> {}
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
      permissionsViewModel.onPermissionsNotGranted()
    } else {
      permissionsViewModel.onPermissionsGranted()
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
    navigateTo(PermissionsFragmentDirections.actionNavigationPermissionsToNavigationMain())
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