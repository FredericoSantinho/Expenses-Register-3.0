package neuro.expenses.register.ui.bills

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.R
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

@Composable
fun BillItemFooterComposable(total: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 4.dp), horizontalArrangement = Arrangement.Center
  ) {
    Text(
      stringResource(R.string.edit_bill_total),
      modifier = Modifier.padding(end = 8.dp),
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      fontWeight = FontWeight.Bold
    )
    Text(
      total,
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      textAlign = TextAlign.End,
      fontWeight = FontWeight.Bold
    )
  }
}

@Preview
@Composable
fun PreviewBillItemFooterComposable() {
  ExpensesRegisterTheme {
    BillItemFooterComposable("10.30 €")
  }
}
