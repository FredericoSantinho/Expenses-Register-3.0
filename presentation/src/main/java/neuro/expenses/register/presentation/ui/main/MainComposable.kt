package neuro.expenses.register.presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import neuro.expenses.register.presentation.common.compose.rememberUnit
import neuro.expenses.register.presentation.ui.common.composables.appbar.SearchAppBar
import neuro.expenses.register.presentation.ui.common.composables.fab.FloatingActionButtonComposable
import neuro.expenses.register.presentation.ui.main.nav.BottomNavigation
import neuro.expenses.register.presentation.ui.main.nav.NavigationGraph
import neuro.expenses.register.viewmodel.main.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainComposable(
  fragmentActivity: FragmentActivity,
  mainViewModel: MainViewModel = getViewModel()
) {
  rememberUnit { mainViewModel.onComposition() }

  val navController = rememberNavController()
  Scaffold(
    topBar = { SearchAppBar(navController, fragmentActivity) },
    bottomBar = { BottomNavigation(navController) },
    floatingActionButton = { FloatingActionButtonComposable() }
  ) {
    NavigationGraph(navController, Modifier.padding(it))
  }
}
