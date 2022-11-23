package neuro.expenses.register.ui.bills.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.viewmodel.edit.bill.EditBillViewModel
import org.koin.androidx.compose.get

@Composable
fun EditBillComposable(
  fragmentActivity: FragmentActivity,
  editBillViewModel: EditBillViewModel = get(),
  value: MutableState<String> = mutableStateOf(""),
  modifier: Modifier = Modifier
) {
  val isError = remember { mutableStateOf(false) }
  Column(
    modifier = modifier
      .background(Color.White)
      .padding(8.dp)
      .verticalScroll(rememberScrollState())
      .fillMaxWidth(),
    horizontalAlignment = CenterHorizontally
  ) {
    DateTimeComposable(fragmentActivity = fragmentActivity, calendar = editBillViewModel.calendar)
    TextFieldWithError(
      label = stringResource(R.string.bills_place),
      value = value,
      isError = isError,
    )
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 8.dp)) {
      Button(onClick = {

      }) {
        Text(text = "save")
      }
      Button(modifier = Modifier.padding(start = 24.dp), onClick = {

      }) {
        Text(text = "cancel")
      }
    }
  }
}