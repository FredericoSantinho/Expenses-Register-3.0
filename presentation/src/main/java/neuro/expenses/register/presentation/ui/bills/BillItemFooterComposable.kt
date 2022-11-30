package neuro.expenses.register.presentation.ui.bills

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import java.util.*

@Composable
fun BillItemFooterComposable(total: String, calendar: MutableState<Calendar>) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(24.dp + 16.dp * 2)
      .padding(top = 16.dp, bottom = 16.dp, end = 8.dp), horizontalArrangement = Arrangement.End
  ) {
    Column(verticalArrangement = Arrangement.Center) {
      DateTimeComposable(
        calendar = calendar
      )
    }
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
      Row {
        Text(
          stringResource(R.string.edit_bill_total),
          modifier = Modifier.padding(start = 24.dp),
          style = MaterialTheme.typography.subtitle1,
          fontWeight = FontWeight.Bold
        )
        Text(
          total,
          modifier = Modifier.padding(start = 12.dp),
          style = MaterialTheme.typography.subtitle1,
          textAlign = TextAlign.End,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}

@Preview
@Composable
fun PreviewBillItemFooterComposable() {
  val total = "10.30 €"
  val calendar = remember { mutableStateOf(Calendar.getInstance()) }

  ExpensesRegisterTheme {
    BillItemFooterComposable(
      total, calendar
    )
  }
}
