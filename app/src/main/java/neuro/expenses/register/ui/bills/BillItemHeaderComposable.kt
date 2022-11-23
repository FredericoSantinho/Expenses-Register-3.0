package neuro.expenses.register.ui.bills

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
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

@Composable
fun BillItemHeaderComposable() {
  Row(modifier = Modifier.padding(bottom = 4.dp)) {
    Text(
      "Place Product",
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.55f)
    )
    Text(
      "Price",
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.15f)
    )
    Text(
      "Amount",
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.13f)
    )
    Text(
      "Sub-total",
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
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
