package neuro.expenses.register.ui.bills.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.grey_fog_light
import neuro.expenses.register.R
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.composables.text.TextFieldWithError

@Composable
fun EditBillComposable(
  fragmentActivity: FragmentActivity,
  value: MutableState<String> = mutableStateOf("")
) {
  val isError = remember { mutableStateOf(false) }
  Card(
    modifier = Modifier
      .fillMaxWidth(),
    elevation = 2.dp,
    backgroundColor = grey_fog_light,
    shape = RoundedCornerShape(corner = CornerSize(8.dp))
  ) {
    Column(
      horizontalAlignment = CenterHorizontally
    ) {
      DateTimeComposable(fragmentActivity = fragmentActivity)
      TextFieldWithError(
        label = stringResource(R.string.bills_place),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
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
}