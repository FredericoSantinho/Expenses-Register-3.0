package neuro.expenses.register.ui.common.composables.fab

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import neuro.expenses.register.viewmodel.main.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FloatingActionButtonComposable(
  mainViewModel: MainViewModel = getViewModel(),
  modifier: Modifier = Modifier
) {
  AnimatedVisibility(
    visible = mainViewModel.floatingActionButtonViewModel.value != null,
    modifier = modifier,
    enter = scaleIn(animationSpec = tween(500)),
    exit = scaleOut(animationSpec = tween(500)),
  ) {
    FloatingActionButton(
      onClick = { mainViewModel.floatingActionButtonViewModel.value?.onClick() }
    ) {
      Icon(Icons.Filled.Add, "")
    }
  }
}