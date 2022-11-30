package neuro.expenses.register.presentation.common.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import neuro.expenses.register.presentation.R
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