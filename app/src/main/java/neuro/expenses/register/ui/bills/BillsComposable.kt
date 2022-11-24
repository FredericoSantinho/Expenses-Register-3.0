package neuro.expenses.register.ui.bills

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import neuro.expenses.register.common.compose.rememberUnit
import neuro.expenses.register.ui.bill.BillComposable
import neuro.expenses.register.ui.home.composable.*
import neuro.expenses.register.ui.theme.grey_fog_lighter
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bills.BillsUiEvent.UiEvent
import neuro.expenses.register.viewmodel.bills.BillsViewModel
import neuro.expenses.register.viewmodel.edit.bill.BillDetailedViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BillsComposable(
  fragmentActivity: FragmentActivity,
  billDetailedViewModel: BillDetailedViewModel = get(),
  billsViewModel: BillsViewModel = getViewModel()
) {
  rememberUnit { billsViewModel.onComposition() }

  val uiEvent = billsViewModel.uiEvent

  val coroutineScope = rememberCoroutineScope()
  val modalBottomSheetState = rememberModalBottomSheetState()

  val bills = billsViewModel.bills.subscribeAsState(initial = emptyList())

  ModalBottomSheetLayout(modalBottomSheetState,
    { EditBillComposable(fragmentActivity, billDetailedViewModel) }) {
    LazyColumn(
      Modifier
        .background(color = grey_fog_lighter)
        .fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      reverseLayout = true
    ) {
      items(bills.value, { listItem: BillViewModel -> listItem.id }) { item ->
        var unread by remember { mutableStateOf(false) }
        val dismissState = rememberDismissState(confirmStateChange = {
          if (it == DismissValue.DismissedToEnd) unread = !unread
          true
        })
        if (dismissState.isDismissed(DismissDirection.EndToStart) || dismissState.isDismissed(
            DismissDirection.StartToEnd
          )
        ) {
          billsViewModel.onBillSwipe(item)
        }
        SwipeToDismiss(modifier = Modifier.padding(start = 4.dp, end = 4.dp),
          state = dismissState,
          directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
          background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
              when (dismissState.targetValue) {
                DismissValue.Default -> Color.LightGray
                DismissValue.DismissedToEnd -> Color.Transparent
                DismissValue.DismissedToStart -> Color.Transparent
              }
            )
            val alignment = when (direction) {
              DismissDirection.StartToEnd -> Alignment.CenterStart
              DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
              DismissDirection.StartToEnd -> Icons.Default.Delete
              DismissDirection.EndToStart -> Icons.Default.Delete
            }
            val scale by animateFloatAsState(
              if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )

            Box(
              Modifier
                .fillMaxSize()
                .background(color)
                .padding(horizontal = 20.dp),
              contentAlignment = alignment
            ) {
              Icon(
                icon, contentDescription = null, modifier = Modifier.scale(scale)
              )
            }
          },
          dismissContent = {
            BillComposable(item)
          },
          dismissThresholds = {
            FractionalThreshold(1.0f)
          })
      }
    }
  }
  onUiEvent(uiEvent, coroutineScope, modalBottomSheetState, billsViewModel)

  addBackHandler(modalBottomSheetState, coroutineScope)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onUiEvent(
  uiEvent: State<UiEvent?>,
  coroutineScope: CoroutineScope,
  modalBottomSheetState: ModalBottomSheetState,
  billsViewModel: BillsViewModel
) {
  when (uiEvent.value) {
    is UiEvent.OpenBillDetailed -> showModalBottomSheet(
      uiEvent.value, coroutineScope, modalBottomSheetState
    )
    is UiEvent.CloseBillDetailed -> hideModalBottomSheet(
      uiEvent.value, coroutineScope, modalBottomSheetState
    )
    null -> {}
  }
  billsViewModel.eventConsumed()
}
