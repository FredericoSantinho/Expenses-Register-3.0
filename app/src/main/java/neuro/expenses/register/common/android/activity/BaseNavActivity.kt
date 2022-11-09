package neuro.expenses.register.common.android.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import neuro.expenses.register.R
import org.koin.core.component.KoinComponent

open class BaseNavActivity : AppCompatActivity(), KoinComponent {
  protected fun navigateTo(directions: NavDirections) {
    navController().navigate(directions)
  }

  protected fun navigateUp() {
    navController().navigateUp()
  }

  private fun navController(): NavController {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    return navController
  }
}