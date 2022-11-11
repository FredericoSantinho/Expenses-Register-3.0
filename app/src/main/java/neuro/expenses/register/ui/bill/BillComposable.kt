package neuro.expenses.register.ui.bill

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import neuro.expenses.register.R
import neuro.expenses.register.common.shimmer.shimmer
import neuro.expenses.register.common.shimmer.shimmerBackground
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.UiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillComposable(
  billViewModel: BillViewModel,
  modifier: Modifier = Modifier
) {
  val uiState by billViewModel.uiState

  val loading = remember { mutableStateOf(billViewModel.uiState.value == UiState.Loading) }

  Card(
    modifier = modifier
      .requiredHeight(80.dp)
      .fillMaxWidth()
      .combinedClickable(
        onClick = { billViewModel.onClick() },
        onLongClick = { billViewModel.onLongClick() },
      ),
    elevation = 2.dp,
    backgroundColor = Color.White,
    shape = RoundedCornerShape(corner = CornerSize(8.dp))
  ) {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .shimmer(loading.value)
    ) {
      val (imageC, placeC, dateC, totalC, closeBillC) = createRefs()

      AsyncImage(
        modifier = Modifier
          .padding(start = 8.dp)
          .constrainAs(imageC) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          }
          .size(64.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
          .shimmerBackground(loading.value),
        url = billViewModel.iconUrl.value,
        loading
      )
      Text(
        modifier = Modifier
          .constrainAs(placeC) {
            start.linkTo(imageC.end)
            end.linkTo(totalC.start)
            top.linkTo(parent.top)
          }
          .widthIn(160.dp)
          .shimmerBackground(loading.value),
        textAlign = TextAlign.Center,
        text = billViewModel.place.value,
        style = typography.h5,
        fontWeight = FontWeight.Bold
      )
      Row(modifier = Modifier.constrainAs(dateC) {
        start.linkTo(imageC.end)
        end.linkTo(totalC.start)
        bottom.linkTo(parent.bottom)
      }) {
        Text(
          modifier = Modifier
            .widthIn(48.dp)
            .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.time.value,
          style = typography.body2
        )
        Text(
          modifier = Modifier
            .padding(start = 8.dp)
            .widthIn(80.dp)
            .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.date.value,
          style = typography.body2
        )
      }
      Text(
        modifier = Modifier
          .constrainAs(totalC) {
            end.linkTo(closeBillC.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          }
          .widthIn(80.dp)
          .shimmerBackground(loading.value),
        textAlign = TextAlign.End,
        text = billViewModel.total.value,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary
      )
      val iconConstraintModifier = Modifier
        .constrainAs(closeBillC) {
          end.linkTo(parent.end)
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        }
      onUiState(uiState, billViewModel, iconConstraintModifier, loading)
    }
  }
}

@Composable
private fun onUiState(
  uiState: UiState,
  billViewModel: BillViewModel,
  modifier: Modifier,
  loading: MutableState<Boolean>
) {
  when (uiState) {
    is UiState.BillEditable -> {
      editBillIcon(modifier, billViewModel)
      loading.value = false
    }
    is UiState.BillOpen -> {
      closeBillIcon(modifier)
      loading.value = false
    }
    is UiState.BillClosed -> {
      guideDivider(modifier)
      loading.value = false
    }
    is UiState.Loading -> {
      guideDivider(modifier)
      loading.value = true
    }
  }
}

@Composable
private fun guideDivider(iconConstraintModifier: Modifier) {
  Divider(
    modifier = iconConstraintModifier
      .fillMaxHeight()
      .width(0.dp)
  )
}

@Composable
private fun closeBillIcon(imageConstraintModifier: Modifier) {
  IconButton(
    onClick = {

    }, modifier = imageConstraintModifier
      .padding(start = 8.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_close_bill_24),
      contentDescription = null,
      tint = Color.Black
    )
  }
}

@Composable
private fun editBillIcon(modifier: Modifier, billViewModel: BillViewModel) {
  IconButton(
    onClick = {
      billViewModel.onEditClick()
    }, modifier = modifier
      .padding(start = 8.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_edit_24),
      contentDescription = null,
      tint = Color.Black
    )
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  ExpensesRegisterTheme {
    BillComposable(BillViewModel())
  }
}