package neuro.expenses.register.presentation.ui.bills

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme

@Composable
fun BillItemHeaderComposable() {
  Row(modifier = Modifier.padding(bottom = 4.dp)) {
    Text(
      "Place Product",
      style = MaterialTheme.typography.subtitle2,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.55f)
    )
    Text(
      "Price",
      style = MaterialTheme.typography.subtitle2,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.15f)
    )
    Text(
      "Amount",
      style = MaterialTheme.typography.subtitle2,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.13f)
    )
    Text(
      "Sub-total",
      style = MaterialTheme.typography.subtitle2,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.17f)
    )
  }
}

@Preview
@Composable
fun PreviewBillItemHeaderComposable() {
  ExpensesRegisterTheme {
    BillItemHeaderComposable()
  }
}
