package neuro.expenses.register.ui.home.composable

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import neuro.expenses.register.common.back.BackNavElement
import neuro.expenses.register.common.back.DefaultBackHandler
import neuro.expenses.register.common.back.FinishActivityHandler
import neuro.expenses.register.common.back.modalBackNavElement

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun rememberModalBottomSheetState(): ModalBottomSheetState {
  return rememberModalBottomSheetState(
    ModalBottomSheetValue.Hidden, confirmStateChange = {
      it != ModalBottomSheetValue.HalfExpanded
    }, skipHalfExpanded = true
  )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ModalBottomSheetLayout(
  modalBottomSheetState: ModalBottomSheetState,
  modalContent: @Composable () -> (Unit),
  content: @Composable () -> (Unit)
) {
  ModalBottomSheetLayout(sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    sheetBackgroundColor = Color.Transparent,
    sheetState = modalBottomSheetState,
    sheetContent = {
      if (modalBottomSheetState.isVisible) {
        modalContent()
      } else {
        // Needed to prevent EditPlaceProductComposable to be seen when keyboard is hiding as the recomposition only occurs afterwards.
        Divider()
      }
    }) { content() }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun addBackHandler(
  modalBottomSheetState: ModalBottomSheetState, coroutineScope: CoroutineScope
) {
  val activity = LocalContext.current as? Activity
  DefaultBackHandler(
    BackNavElement.default(
      modalBackNavElement(modalBottomSheetState, coroutineScope), FinishActivityHandler(activity)
    )
  )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun showModalBottomSheet(
  key: Any?, coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  LaunchedEffect(key) {
    coroutineScope.launch {
      modalBottomSheetState.show()
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun hideModalBottomSheet(
  key: Any?, coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  LaunchedEffect(key) {
    coroutineScope.launch {
      modalBottomSheetState.hide()
    }
  }
}
