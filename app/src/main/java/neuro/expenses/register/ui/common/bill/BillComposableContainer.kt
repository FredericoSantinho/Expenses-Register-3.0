package neuro.expenses.register.ui.common.bill

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.home.view.model.BillViewModel

@Composable
fun BillComposableContainer(billViewModel: BillViewModel, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Divider(thickness = 2.dp, color = Color.LightGray)
    BillComposable(billViewModel = billViewModel)
    Divider(thickness = 2.dp, color = Color.LightGray)
  }
}
