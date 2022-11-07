package neuro.expenses.register

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import neuro.expenses.register.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private val PERMISSIONS_REQUEST_CODE = 1

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("DEPRECATION")
    // We need to use this method as our minSdk is < 30.
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navView: BottomNavigationView = binding.navView

    val navController = findNavController(R.id.nav_host_fragment_activity_main)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_home,
        R.id.navigation_manual_register,
        R.id.navigation_bills,
        R.id.navigation_edit
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

    disableBottomNavigation()
    checkPermissions(this)
  }

  private fun checkPermissions(activity: AppCompatActivity) {
    if (ContextCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      showPermissionsDialog()
    } else {
      enableBottomNavigation()
    }
  }

  private fun showPermissionsDialog() {
    AlertDialog.Builder(this)
      .setTitle(R.string.permissions_title)
      .setMessage(R.string.permissions_text)
      .setPositiveButton(android.R.string.ok, { dialog, which ->
        requestPermissions(this)
      })
      .setNegativeButton(android.R.string.cancel, { dialog, which ->
        finish()
      })
      .setIcon(android.R.drawable.ic_dialog_alert)
      .show()
  }

  private fun requestPermissions(activity: AppCompatActivity) {
    ActivityCompat.requestPermissions(
      activity,
      arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
      ),
      PERMISSIONS_REQUEST_CODE
    )
  }

  private fun enableBottomNavigation() {
    setNavViewEnabled(true)
  }

  private fun disableBottomNavigation() {
    setNavViewEnabled(false)
  }

  private fun setNavViewEnabled(enabled: Boolean) {
    binding.navView.menu.forEach { it.isEnabled = enabled }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    if (requestCode == PERMISSIONS_REQUEST_CODE) {
      checkPermissions(this)
    }
  }
}