package neuro.expenses.register.presentation.ui.common.composables.fab

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState
import org.koin.androidx.compose.get

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FloatingActionButtonComposable(
  modifier: Modifier = Modifier,
  scaffoldViewModelState: ScaffoldViewModelState = get()
) {
  AnimatedVisibility(
    visible = scaffoldViewModelState.floatingActionButtonViewModel.value != null,
    modifier = modifier,
    enter = scaleIn(animationSpec = tween(500)),
    exit = scaleOut(animationSpec = tween(500)),
  ) {
    FloatingActionButton(
      onClick = { scaffoldViewModelState.floatingActionButtonViewModel.value?.onClick() }
    ) {
      Icon(Icons.Filled.Add, "")
    }
  }
}