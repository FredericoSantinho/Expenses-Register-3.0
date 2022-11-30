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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
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
import neuro.expenses.register.viewmodel.bill.BillUiState.UiState
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.IBillViewModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillCardComposable(
  billViewModel: IBillViewModel, modifier: Modifier = Modifier
) {
  val roundedCornerShape = RoundedCornerShape(corner = CornerSize(8.dp))

  val uiState by billViewModel.uiState

  val loading = remember { mutableStateOf(billViewModel.uiState.value == UiState.Loading) }

  Card(modifier = modifier
    .semantics { testTag = BillTags.CARD }
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
        .semantics { testTag = billViewModel.iconUrl.value }
        .constrainAs(imageC) {
          start.linkTo(parent.start)
          linkTo(top = parent.top, bottom = parent.bottom)
        }
        .size(64.dp)
        .clip(roundedCornerShape)
        .shimmerBackground(loading.value), billViewModel.iconUrl.value, loading)
      Text(modifier = Modifier
        .semantics { testTag = BillTags.PLACE }
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
        Text(modifier = Modifier
          .semantics { testTag = BillTags.TIME }
          .widthIn(48.dp)
          .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.time.value,
          style = typography.body2)
        Text(modifier = Modifier
          .semantics { testTag = BillTags.DATE }
          .padding(start = 8.dp)
          .widthIn(80.dp)
          .shimmerBackground(loading.value),
          textAlign = TextAlign.Center,
          text = billViewModel.date.value,
          style = typography.body2)
      }
      Text(modifier = Modifier
        .semantics { testTag = BillTags.TOTAL }
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
  IconButton(onClick = {

  },
    modifier = imageConstraintModifier
      .semantics { testTag = BillTags.CLOSE_BILL_ICON }
      .padding(start = 8.dp)) {
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
    BillCardComposable(BillViewModel(billModel = billModelMock()))
  }
}

class BillTags {
  companion object {
    const val CARD = "card"
    const val PLACE = "place"
    const val TIME = "time"
    const val DATE = "date"
    const val TOTAL = "total"
    const val CLOSE_BILL_ICON = "closeIcon"
  }
}