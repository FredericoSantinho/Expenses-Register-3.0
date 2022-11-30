package neuro.expenses.register.ui.home.composable

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import neuro.expenses.register.presentation.common.back.*

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
  onModalBottomSheetVisible: () -> Unit = {},
  onModalBottomSheetNotVisible: () -> Unit = {},
  modalContent: @Composable () -> (Unit),
  content: @Composable () -> (Unit)
) {
  ModalBottomSheetLayout(sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    sheetBackgroundColor = Color.Transparent,
    sheetState = modalBottomSheetState,
    sheetContent = {
      if (modalBottomSheetState.isVisible) {
        onModalBottomSheetVisible()
        modalContent()
      } else {
        onModalBottomSheetNotVisible()
        // Needed to prevent Modal content to be seen when keyboard is hiding as the recomposition only occurs afterwards.
        Divider()
      }
    }) { content() }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun addBackHandler(
  modalBottomSheetState: ModalBottomSheetState,
  coroutineScope: CoroutineScope,
  navController: NavHostController
) {
  val activity = LocalContext.current as? Activity

  DefaultBackHandler(
    BackNavElement.default(
      modalBackNavElement(modalBottomSheetState, coroutineScope),
      NavHostControllerHandler(navController, activity)
    )
  )
}

@OptIn(ExperimentalMaterialApi::class)
fun showModalBottomSheet(
  coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  coroutineScope.launch {
    modalBottomSheetState.show()
  }
}

@OptIn(ExperimentalMaterialApi::class)
fun hideModalBottomSheet(
  coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  coroutineScope.launch {
    modalBottomSheetState.hide()
  }
}
