package neuro.expenses.register.presentation.ui.bill

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel

@Composable
fun BillCardComposableContainer(
  billCardViewModel: IBillCardViewModel,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Divider(thickness = 2.dp, color = Color.LightGray)
    BillCardComposable(billCardViewModel = billCardViewModel)
    Divider(thickness = 2.dp, color = Color.LightGray)
  }
}
