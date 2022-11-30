package neuro.expenses.register.presentation.common.back

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun modalBackNavElement(
  state: ModalBottomSheetState,
  coroutineScope: CoroutineScope,
  callback: (() -> Unit)? = null
) = BackNavElement.needsProcessing {
  if (state.isVisible) {
    state.hideAnd(coroutineScope) { callback?.invoke() }
    BackNavElement.Result.CANNOT_GO_BACK
  } else {
    BackNavElement.Result.CAN_GO_BACK
  }
}
