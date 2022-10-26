package neuro.expenses.register.ui.manual.register.composable

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R
import neuro.expenses.register.common.picker.date.ShowDatePicker
import neuro.expenses.register.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.common.picker.time.ShowTimePicker
import neuro.expenses.register.ui.composables.text.CurrencyTextField
import neuro.expenses.register.ui.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.composables.text.TextFieldWithError
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import neuro.expenses.register.ui.manual.register.composable.datetime.DateTimeComposable
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapperImpl
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapperImpl

@Composable
fun ManualRegisterComposable(
  manualRegisterViewModel: ManualRegisterViewModel,
  appCompatActivity: FragmentActivity,
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl(),
  currency: String = "€"
) {
  Column(
    Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.Bottom
  ) {
    val amountVar = remember { mutableStateOf(0.0) }
    val priceVar = remember { mutableStateOf(0.0) }

    val dateTimeComposable = DateTimeComposable(
      appCompatActivity,
      showTimePicker,
      showDatePicker,
      timeTextMapper,
      dateTextMapper,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    )
    TextFieldWithError(
      label = stringResource(R.string.manual_register_description),
      keyboardOptions = keyboardOptionsText,
      modifier = Modifier.padding(start = 8.dp, end = 8.dp),
      state = manualRegisterViewModel.description
    )
    TextFieldWithDropdown(
      dataIn = manualRegisterViewModel.getCategories(),
      label = stringResource(R.string.manual_register_category),
      keyboardOptions = keyboardOptionsText,
      modifier = Modifier.padding(start = 8.dp, end = 8.dp),
      state = manualRegisterViewModel.category
    )
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
      val (place, placeAuto) = createRefs()

      val placeTF = TextFieldWithError(
        label = stringResource(R.string.manual_register_place),
        modifier = Modifier.constrainAs(place) {
          start.linkTo(parent.start, margin = 8.dp)
          end.linkTo(placeAuto.start, margin = 8.dp)
          width = Dimension.fillToConstraints
        },
        keyboardOptions = keyboardOptionsText,
        state = manualRegisterViewModel.place
      )
      IconButton(onClick = {
        placeTF.setText(manualRegisterViewModel.getNearestPlace())
      }, modifier = Modifier.constrainAs(placeAuto) {
        end.linkTo(parent.end, margin = 8.dp)
        top.linkTo(place.top, margin = 8.dp)
        bottom.linkTo(place.bottom)
      }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_edit_place_24),
          contentDescription = null,
          tint = MaterialTheme.colors.primary
        )
      }
    }
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
      val (price, amount, totalLabel, total) = createRefs()

      val totalVar =
        remember { mutableStateOf(getTotalStr(amountVar.value, priceVar.value, currency)) }
      CurrencyTextField(
        label = stringResource(R.string.manual_register_price),
        modifier = Modifier.constrainAs(price) {
          start.linkTo(parent.start, margin = 8.dp)
          width = Dimension.value(96.dp)
        },
        keyboardOptions = keyboardOptionsNumeric,
        onValueChange = {
          priceVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
          totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
        },
        state = manualRegisterViewModel.price
      )
      TextFieldWithError(
        label = stringResource(R.string.manual_register_amount),
        modifier = Modifier.constrainAs(amount) {
          start.linkTo(price.end, margin = 8.dp)
          width = Dimension.value(96.dp)
        },
        keyboardOptions = keyboardOptionsNumeric,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        onValueChange = {
          amountVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
          totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
        },
        state = manualRegisterViewModel.amount
      )
      Text(
        text = stringResource(R.string.manual_register_total) + ':',
        modifier = Modifier.constrainAs(totalLabel) {
          end.linkTo(total.start, margin = 8.dp)
          top.linkTo(amount.top, margin = 8.dp)
          bottom.linkTo(amount.bottom)
        },
        fontSize = 16.sp
      )
      Text(text = totalVar.value, modifier = Modifier.constrainAs(total) {
        end.linkTo(parent.end, margin = 16.dp)
        top.linkTo(amount.top, margin = 8.dp)
        bottom.linkTo(amount.bottom)
      }, fontSize = 16.sp)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
      Button(onClick = {
        val date = dateTimeComposable.getDateTime().date
        val time = dateTimeComposable.getDateTime().time

        manualRegisterViewModel.register(time, date)
      }, modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
        Text(text = stringResource(R.string.manual_register_register))
      }
    }
  }
}

private fun getTotalStr(amount: Double, price: Double, currency: String): String {
  return (amount * price).toString() + ' ' + currency
}

private val keyboardOptionsNumeric = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
)

private val keyboardOptionsText = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
)

@Preview
@Composable
fun PreviewManualRegisterComposable() {
  ExpensesRegisterTheme {
    ManualRegisterComposable(
      ManualRegisterViewModel(), AppCompatActivity()
    )
  }
}
