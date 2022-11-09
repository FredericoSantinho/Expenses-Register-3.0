package neuro.expenses.register.ui.bills.composable

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.grey_fog_lighter
import kotlinx.coroutines.launch
import neuro.expenses.register.ui.bills.BillsViewModel
import neuro.expenses.register.ui.common.bill.BillComposable
import neuro.expenses.register.ui.common.bill.BillViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BillsComposable(
  billsViewModel: BillsViewModel = getViewModel(),
  fragmentActivity: FragmentActivity
) {
  val coroutineScope = rememberCoroutineScope()

  val modalBottomSheetValue = if (billsViewModel.isEditMode.value)
    ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden

  val modalBottomSheetState =
    rememberModalBottomSheetState(
      modalBottomSheetValue,
      confirmStateChange = {
        it != ModalBottomSheetValue.HalfExpanded
      }
    )

  Column(Modifier.background(color = grey_fog_lighter)) {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxSize()
        .background(color = grey_fog_lighter)
    ) {
      val (lazyColumnC, editBillC) = createRefs()

      LazyColumn(
        Modifier
          .constrainAs(lazyColumnC) {
            top.linkTo(parent.top)
            bottom.linkTo(editBillC.top)
            height = Dimension.fillToConstraints
          }, verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(billsViewModel.bills, { listItem: BillViewModel -> listItem.id }) { item ->
          var unread by remember { mutableStateOf(false) }
          val dismissState = rememberDismissState(
            confirmStateChange = {
              if (it == DismissValue.DismissedToEnd) unread = !unread
              true
            }
          )
          if (dismissState.isDismissed(DismissDirection.EndToStart) ||
            dismissState.isDismissed(DismissDirection.StartToEnd)
          ) {
            billsViewModel.onBillSwipe(item)
          }
          SwipeToDismiss(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            state = dismissState,
            directions = if (billsViewModel.isEditMode.value) setOf() else setOf(
              DismissDirection.StartToEnd,
              DismissDirection.EndToStart
            ),
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
                  icon,
                  contentDescription = null,
                  modifier = Modifier.scale(scale)
                )
              }
            },
            dismissContent = {
              BillComposable(item)
            }
          )
        }
      }
      ModalBottomSheetLayout(
        sheetBackgroundColor = Color.Transparent,
        sheetState = modalBottomSheetState,
        sheetContent = {
          EditBillComposable(fragmentActivity)
        }
      ) {}
      val isEditMode = billsViewModel.isEditMode.value
      if (isEditMode) {
        LaunchedEffect(isEditMode) {
          coroutineScope.launch {
            modalBottomSheetState.show()
          }
        }
      } else {
        LaunchedEffect(isEditMode) {
          coroutineScope.launch {
            modalBottomSheetState.hide()
          }
        }
      }
    }
  }
}