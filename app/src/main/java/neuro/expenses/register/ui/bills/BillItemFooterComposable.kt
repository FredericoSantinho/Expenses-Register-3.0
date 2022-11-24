package neuro.expenses.register.ui.bills

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
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import java.util.*

@Composable
fun BillItemFooterComposable(
  fragmentActivity: FragmentActivity, total: String, calendar: MutableState<Calendar>
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 16.dp, bottom = 16.dp, end = 8.dp),
    horizontalArrangement = Arrangement.End
  ) {
    DateTimeComposable(
      fragmentActivity = fragmentActivity, calendar = calendar
    )
    Column(verticalArrangement = Arrangement.Center) {
      Row {
        Text(
          stringResource(R.string.edit_bill_total),
          modifier = Modifier.padding(start = 32.dp),
          fontSize = MaterialTheme.typography.subtitle1.fontSize.times(1.125),
          fontWeight = FontWeight.Bold
        )
        Text(
          total,
          modifier = Modifier.padding(start = 12.dp),
          fontSize = MaterialTheme.typography.subtitle1.fontSize.times(1.125),
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
  val calendar = remember { mutableStateOf(Calendar.getInstance()) }
  ExpensesRegisterTheme {
    BillItemFooterComposable(
      FragmentActivity(), "10.30 €", calendar
    )
  }
}
