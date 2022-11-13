package neuro.expenses.register

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import neuro.expenses.register.common.activity.BaseActivity
import neuro.expenses.register.databinding.ActivityMainBinding
import neuro.expenses.register.ui.settings.SettingsActivity
import neuro.expenses.register.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
  private val mainViewModel: MainViewModel by viewModel()

  override fun setupViewModel() {
    super.setupViewModel()

    mainViewModel.uiEvent.observe(this) { onUiEvent(it) }
  }

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

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)

    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == R.id.config_button) {
      mainViewModel.onConfigButton()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun onUiEvent(uiEvent: MainViewModel.UiEvent?) {
    when (uiEvent) {
      is MainViewModel.UiEvent.NavigateToSettings -> startSettingsActivity()
      null -> {}
    }
  }

  private fun startSettingsActivity() {
    val i = Intent(this, SettingsActivity::class.java)
    startActivity(i)
  }
}