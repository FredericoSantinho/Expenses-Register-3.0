package neuro.expenses.register.presentation.ui.common.composables.snackbar

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import neuro.expenses.register.presentation.R

@Composable
fun showSnackbar(text: String, key: Any) {
  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }
  val message = stringResource(R.string.manual_register_register_success, text)
  LaunchedEffect(key) {
    scope.launch {
      snackbarHostState.showSnackbar(message)
    }
  }
  SnackbarHost(hostState = snackbarHostState)
}
