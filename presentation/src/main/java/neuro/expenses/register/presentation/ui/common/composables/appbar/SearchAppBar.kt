package neuro.expenses.register.presentation.ui.common.composables.appbar

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import neuro.expenses.register.presentation.ui.common.composables.appbar.mapper.toPresentation
import neuro.expenses.register.presentation.ui.common.composables.search.SearchWithDropdown
import neuro.expenses.register.presentation.ui.common.mapper.toPresentation
import neuro.expenses.register.viewmodel.appbar.AppBarUiEvent.UiEvent
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState
import org.koin.androidx.compose.get

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
  navController: NavController,
  fragmentActivity: FragmentActivity,
  navigateToSettings: NavigateToSettings = get(),
  scaffoldViewModelState: ScaffoldViewModelState = get()
) {
  val appBarViewModel = scaffoldViewModelState.appBarViewModel.value
  val uiEvent by appBarViewModel.uiEvent

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
      },
      actions = {
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
              dataIn = appBarViewModel.dataIn.value.map { it.toPresentation() },
              onValueChange = { appBarViewModel.onValueChange(it) },
              searchViewModel = appBarViewModel.searchViewModel,
              hint = stringResource(appBarViewModel.searchHint.value.toPresentation())
            )
          } else {
            Text(
              appBarViewModel.title.value.toPresentation(),
              Modifier.constrainAs(searchC) {
                linkTo(top = parent.top, bottom = parent.bottom)
              },
              color = Color.White,
              style = MaterialTheme.typography.h6,
              fontWeight = FontWeight.Bold
            )
          }
          if (appBarViewModel.searchEnabled.value) {
            IconButton(modifier = Modifier.constrainAs(searchIconC) {
              end.linkTo(configIconC.start)
              linkTo(top = parent.top, bottom = parent.bottom)
            }, onClick = {
              appBarViewModel.onSearchButton()
            }) {
              Icon(Icons.Filled.Search, null, tint = Color.White)
            }
          }
          IconButton(modifier = Modifier.constrainAs(configIconC) {
            linkTo(top = parent.top, bottom = parent.bottom)
            end.linkTo(parent.end)
          }, onClick = { appBarViewModel.onSettingsButton() }) {
            Icon(Icons.Filled.Settings, null, tint = Color.White)
          }
        }
      })
  }
  onUiEvent(uiEvent, navigateToSettings, fragmentActivity, focusRequester, appBarViewModel)
}

@Composable
private fun onUiEvent(
  uiEvent: UiEvent?,
  navigateToSettings: NavigateToSettings,
  context: Context,
  focusRequester: FocusRequester,
  appBarViewModel: AppBarViewModel
) {
  when (uiEvent) {
    is UiEvent.NavigateToSettings -> {
      navigateToSettings(navigateToSettings, context)
    }
    is UiEvent.FocusSearch -> focusSearch(uiEvent, focusRequester)
    else -> {}
  }
  appBarViewModel.eventConsumed()
}

@Composable
fun focusSearch(uiEvent: UiEvent, focusRequester: FocusRequester) {
  LaunchedEffect(uiEvent) {
    focusRequester.requestFocus()
  }
}

fun navigateToSettings(navigateToSettings: NavigateToSettings, context: Context) {
  navigateToSettings.navigateToSettings(context)
}
