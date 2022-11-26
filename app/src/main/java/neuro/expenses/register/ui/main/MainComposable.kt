package neuro.expenses.register.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.ui.common.composables.appbar.SearchAppBar
import neuro.expenses.register.ui.common.composables.fab.FloatingActionButtonComposable
import neuro.expenses.register.ui.main.nav.BottomNavigation
import neuro.expenses.register.ui.main.nav.NavigationGraph

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainComposable(fragmentActivity: FragmentActivity) {
  val navController = rememberNavController()
  Scaffold(
    topBar = { SearchAppBar(navController, fragmentActivity) },
    bottomBar = { BottomNavigation(navController) },
    floatingActionButton = { FloatingActionButtonComposable() }
  ) {
    NavigationGraph(navController, fragmentActivity, Modifier.padding(it))
  }
}
