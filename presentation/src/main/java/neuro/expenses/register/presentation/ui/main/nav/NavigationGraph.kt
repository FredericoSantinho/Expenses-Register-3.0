package neuro.expenses.register.presentation.ui.main.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import neuro.expenses.register.presentation.ui.bills.BillsComposable
import neuro.expenses.register.presentation.ui.edit.EditComposable
import neuro.expenses.register.presentation.ui.home.composable.HomeComposable
import neuro.expenses.register.presentation.ui.manual.register.composable.ManualRegisterComposable

@Composable
fun NavigationGraph(
    navController: NavHostController,
    fragmentActivity: FragmentActivity,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute, modifier = modifier) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeComposable(fragmentActivity, navController)
        }
        composable(BottomNavItem.ManualRegister.screenRoute) {
            ManualRegisterComposable(fragmentActivity)
        }
        composable(BottomNavItem.Bills.screenRoute) {
            BillsComposable(fragmentActivity, navController)
        }
        composable(BottomNavItem.Edit.screenRoute) {
            EditComposable()
        }
    }
}
