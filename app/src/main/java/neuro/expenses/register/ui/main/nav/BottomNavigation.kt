package neuro.expenses.register.ui.main.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import neuro.expenses.register.R

@Composable
fun BottomNavigation(navController: NavController) {
  val items = listOf(
    BottomNavItem.Home,
    BottomNavItem.ManualRegister,
    BottomNavItem.Bills,
    BottomNavItem.Edit
  )
  BottomNavigation(
    backgroundColor = MaterialTheme.colors.background,
    contentColor = MaterialTheme.colors.primary
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.forEach { item ->
      BottomNavigationItem(
        icon = {
          Icon(
            painterResource(id = item.icon),
            contentDescription = stringResource(item.stringId)
          )
        },
        label = {
          Text(
            text = stringResource(item.stringId),
            fontSize = 9.sp
          )
        },
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = Color.Black.copy(0.5f),
        alwaysShowLabel = true,
        selected = currentRoute == item.screenRoute,
        onClick = {
          navController.navigate(item.screenRoute) {

            navController.graph.startDestinationRoute?.let { screenRoute ->
              popUpTo(screenRoute) {
                saveState = true
              }
            }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    }
  }
}

sealed class BottomNavItem(
  @StringRes var stringId: Int,
  @DrawableRes var icon: Int,
  var screenRoute: String
) {

  object Home : BottomNavItem(R.string.title_home, R.drawable.ic_home_black_24dp, "home")
  object ManualRegister : BottomNavItem(
    R.string.title_manual_register,
    R.drawable.ic_manual_register_24,
    "manual_register"
  )

  object Bills : BottomNavItem(R.string.title_bills, R.drawable.ic_bill_black_24dp, "bills")
  object Edit : BottomNavItem(R.string.title_edit, R.drawable.ic_edit_24, "edit")
}