package neuro.expenses.register.presentation.common.back

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun ModalBottomSheetState.hideAnd(
  coroutineScope: CoroutineScope,
  thenCallback: () -> Unit
) {
  coroutineScope.launch {
    if (isVisible) {
      hide()
      thenCallback()
    }
  }
}
