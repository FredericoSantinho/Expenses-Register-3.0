package neuro.expenses.register.presentation.ui.bill

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.common.shimmer.shimmer
import neuro.expenses.register.presentation.common.shimmer.shimmerBackground
import neuro.expenses.register.presentation.mocks.bill.billModelMock
import neuro.expenses.register.presentation.ui.common.composables.image.AsyncImage
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.BillCardViewModel
import neuro.expenses.register.viewmodel.bill.BillUiState.UiState
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillCardComposable(
  billViewModel: IBillCardViewModel, modifier: Modifier = Modifier
) {
  val roundedCornerShape = RoundedCornerShape(corner = CornerSize(8.dp))

  val uiState by billViewModel.uiState

  val loading = remember { mutableStateOf(billViewModel.uiState.value == UiState.Loading) }

  Card(
    modifier = modifier
      .testTag(BillCardComposableTags.CARD)
      .clip(roundedCornerShape)
      .requiredHeight(80.dp)
      .fillMaxWidth()
      .combinedClickable(
        onClick = { billViewModel.onCardClick() },
        onLongClick = { billViewModel.onCardLongClick() },
      ),
    elevation = 2.dp,
    backgroundColor = Color.White,
    shape = roundedCornerShape
  ) {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .shimmer(loading.value)
    ) {
      val (imageC, placeC, dateC, totalC, closeBillC) = createRefs()

      AsyncImage(modifier = Modifier
        .testTag(billViewModel.iconUrl.value)
        .constrainAs(imageC) {
          start.linkTo(parent.start)
          linkTo(top = parent.top, bottom = parent.bottom)
        }
        .size(64.dp)
        .clip(roundedCornerShape)
        .shimmerBackground(loading.value), billViewModel.iconUrl.value, loading)
      Text(modifier = Modifier
        .testTag(BillCardComposableTags.PLACE)
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
        fontWeight = FontWeight.Bold)
      Row(modifier = Modifier.constrainAs(dateC) {
        start.linkTo(imageC.end)
        end.linkTo(totalC.start)
        bottom.linkTo(parent.bottom)
      }) {
        Text(
          modifier = Modifier
            .testTag(BillCardComposableTags.TIME)
            .widthIn(48.dp)
            .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.time.value,
          style = typography.body2
        )
        Text(
          modifier = Modifier
            .testTag(BillCardComposableTags.DATE)
            .padding(start = 8.dp)
            .widthIn(80.dp)
            .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.date.value,
          style = typography.body2
        )
      }
      Text(modifier = Modifier
        .testTag(BillCardComposableTags.TOTAL)
        .constrainAs(totalC) {
          end.linkTo(closeBillC.start)
          linkTo(top = parent.top, bottom = parent.bottom)
        }
        .widthIn(80.dp)
        .shimmerBackground(loading.value),
        textAlign = TextAlign.End,
        text = billViewModel.total.value,
        style = typography.h5,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary)
      val iconConstraintModifier = Modifier.constrainAs(closeBillC) {
        end.linkTo(parent.end)
        linkTo(top = parent.top, bottom = parent.bottom)
      }
      onUiState(uiState, iconConstraintModifier, loading)
    }
  }
}

@Composable
private fun onUiState(
  uiState: UiState, modifier: Modifier, loading: MutableState<Boolean>
) {
  when (uiState) {
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

    },
    modifier = imageConstraintModifier
      .testTag(BillCardComposableTags.CLOSE_BILL_ICON)
      .padding(start = 8.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_close_bill_24),
      contentDescription = null,
      tint = Color.Black
    )
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {

  ExpensesRegisterTheme {
    BillCardComposable(BillCardViewModel(billModel = billModelMock()))
  }
}

class BillCardComposableTags {
  companion object {
    const val CARD = "billCard"
    const val PLACE = "billPlace"
    const val TIME = "billTime"
    const val DATE = "billDate"
    const val TOTAL = "billTotal"
    const val CLOSE_BILL_ICON = "billCloseIcon"
  }
}