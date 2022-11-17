package neuro.expenses.register.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.ui.main.nav.BottomNavigation

@Composable
fun MainComposable(fragmentActivity: FragmentActivity) {
  val navController = rememberNavController()
  Scaffold(
    bottomBar = { BottomNavigation(navController) }
  ) {
    NavigationGraph(navController, fragmentActivity, Modifier.padding(it))
  }
}
