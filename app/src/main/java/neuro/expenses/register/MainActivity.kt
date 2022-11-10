package neuro.expenses.register

import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import neuro.expenses.register.common.android.activity.BaseActivity
import neuro.expenses.register.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override fun getViewBinding(): ActivityMainBinding {
    return ActivityMainBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("DEPRECATION")
    // We need to use this method as our minSdk is < 30.
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

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
  }
}