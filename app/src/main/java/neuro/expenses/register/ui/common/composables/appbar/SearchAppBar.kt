package neuro.expenses.register.ui.common.composables.appbar

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import neuro.expenses.register.ui.common.composables.search.NavigateToSettings
import neuro.expenses.register.ui.common.composables.search.SearchWithDropdown
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.UiEvent
import org.koin.androidx.compose.get

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
  navController: NavController,
  fragmentActivity: FragmentActivity,
  navigateToSettings: NavigateToSettings = get(),
  appBarViewModel: AppBarViewModel = get()
) {
  val uiEvent by appBarViewModel.uiEvent.observeAsState(null)

  val focusRequester = remember { FocusRequester() }

  Column {
    TopAppBar(
      elevation = 4.dp,
      title = { },
      backgroundColor = MaterialTheme.colors.primarySurface,
      navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) {
          Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
        }
      }, actions = {
        val dataIn = remember { mutableStateOf(strings()) }

        ConstraintLayout(Modifier.fillMaxSize()) {
          val (searchC, searchIconC, configIconC) = createRefs()

          if (appBarViewModel.searchViewModel.showSearch.value) {
            SearchWithDropdown(
              Modifier
                .constrainAs(searchC) {
                  linkTo(start = parent.start, end = searchIconC.start)
                  width = Dimension.fillToConstraints
                }
                .focusRequester(focusRequester),
              dataIn = dataIn
            )
          } else {
            Text(
              appBarViewModel.title.value,
              Modifier.constrainAs(searchC) {
                linkTo(top = parent.top, bottom = parent.bottom)
              },
              color = Color.White,
              fontSize = MaterialTheme.typography.h6.fontSize,
              fontWeight = FontWeight.Bold
            )
          }
          IconButton(modifier = Modifier.constrainAs(searchIconC) {
            end.linkTo(configIconC.start)
            linkTo(top = parent.top, bottom = parent.bottom)
          }, onClick = {
            appBarViewModel.onSearchButton()
          }) {
            Icon(Icons.Filled.Search, null, tint = Color.White)
          }
          IconButton(
            modifier = Modifier.constrainAs(configIconC) {
              end.linkTo(parent.end)
              linkTo(top = parent.top, bottom = parent.bottom)
            },
            onClick = { appBarViewModel.onSettingsButton() }) {
            Icon(Icons.Filled.Settings, null, tint = Color.White)
          }
        }


      })
    if (appBarViewModel.searchViewModel.showSearch.value) {
      LaunchedEffect(Unit) {
        focusRequester.requestFocus()
      }
    }
  }
  onUiEvent(uiEvent, navigateToSettings, fragmentActivity)
}

private fun strings(): List<String> {
  val list: MutableList<String> = mutableListOf()
  val s = "aab"
  for (i in 1..10) {
    list.add(s.replace('b', Char('b'.code + i)))
  }
  return list
}

@Composable
private fun onUiEvent(
  uiEvent: UiEvent?,
  navigateToSettings: NavigateToSettings,
  context: Context
) {
  when (uiEvent) {
    is UiEvent.NavigateToSettings -> {
      navigateToSettings(navigateToSettings, context)
    }
    else -> {}
  }
}

fun navigateToSettings(navigateToSettings: NavigateToSettings, context: Context) {
  navigateToSettings.navigateToSettings(context)
}
