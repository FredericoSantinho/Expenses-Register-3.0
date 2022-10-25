package neuro.expenses.register.ui.manual.register.composable

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import neuro.expenses.register.common.picker.date.OnSetDate
import neuro.expenses.register.common.picker.date.ShowDatePicker
import neuro.expenses.register.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.common.picker.time.OnSetTime
import neuro.expenses.register.common.picker.time.ShowTimePicker
import neuro.expenses.register.ui.composables.text.CurrencyTextField
import neuro.expenses.register.ui.composables.text.TextField
import neuro.expenses.register.ui.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapperImpl
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapperImpl
import java.util.*

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
  ConstraintLayout {
    val (dateTimeHolder, description, category, place, placeAuto, price, amount, totalLabel, total, button) = createRefs()

    val amountVar = remember { mutableStateOf(0.0) }
    val priceVar = remember { mutableStateOf(0.0) }

    val calendar: Calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val timestamp = calendar.time.time

    val (time, timePicker, date, datePicker) = createRefs()

    ConstraintLayout(modifier = Modifier.constrainAs(dateTimeHolder) {
      bottom.linkTo(description.top, margin = 8.dp)
      start.linkTo(parent.start, margin = 8.dp)
      end.linkTo(parent.end, margin = 8.dp)
    }) {
      var timeText by remember { mutableStateOf(timeTextMapper.map(hour, minute)) }
      Text(text = timeText, modifier = Modifier.constrainAs(time) {
        bottom.linkTo(parent.bottom)
      })
      IconButton(onClick = {
        showTimePicker.showTimePicker(appCompatActivity, object : OnSetTime {
          override fun onSetTime(hour: Int, minute: Int) {
            timeText = timeTextMapper.map(hour, minute)
          }
        })
      }, modifier = Modifier.constrainAs(timePicker) {
        top.linkTo(time.top)
        bottom.linkTo(time.bottom)
        start.linkTo(time.end)
      }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_change_time_24),
          contentDescription = null,
          tint = MaterialTheme.colors.primary
        )
      }
      var dateText by remember { mutableStateOf(dateTextMapper.map(timestamp)) }
      Text(text = dateText, modifier = Modifier.constrainAs(date) {
        bottom.linkTo(parent.bottom)
        start.linkTo(timePicker.end)
      })
      IconButton(onClick = {
        showDatePicker.showDatePicker(appCompatActivity, object : OnSetDate {
          override fun onSetDate(timestamp: Long) {
            dateText = dateTextMapper.map(timestamp)
          }
        })
      }, modifier = Modifier.constrainAs(datePicker) {
        top.linkTo(time.top)
        bottom.linkTo(time.bottom)
        start.linkTo(date.end)
      }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_change_date_24),
          contentDescription = null,
          tint = MaterialTheme.colors.primary
        )
      }
    }
    val descriptionTF = TextField(
      label = stringResource(R.string.manual_register_description),
      modifier = Modifier.constrainAs(description) {
        bottom.linkTo(category.top)
        start.linkTo(parent.start, margin = 8.dp)
        end.linkTo(parent.end, margin = 8.dp)
        width = Dimension.matchParent
      },
      keyboardOptions = keyboardOptionsText
    )
    val categoryTF = TextFieldWithDropdown(
      dataIn = manualRegisterViewModel.getCategories(),
      label = stringResource(R.string.manual_register_category),
      modifier = Modifier.constrainAs(category) {
        bottom.linkTo(place.top)
        start.linkTo(parent.start, margin = 8.dp)
        end.linkTo(parent.end, margin = 8.dp)
        width = Dimension.matchParent
      },
      keyboardOptions = keyboardOptionsText
    )
    val placeTF = TextField(
      label = stringResource(R.string.manual_register_place),
      modifier = Modifier.constrainAs(place) {
        bottom.linkTo(price.top)
        start.linkTo(parent.start, margin = 8.dp)
        end.linkTo(placeAuto.start, margin = 8.dp)
        width = Dimension.fillToConstraints
      },
      keyboardOptions = keyboardOptionsText
    )
    IconButton(onClick = {
      placeTF.setText(manualRegisterViewModel.getNearestPlace())
    }, modifier = Modifier.constrainAs(placeAuto) {
      bottom.linkTo(price.top)
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
    val totalVar =
      remember { mutableStateOf(getTotalStr(amountVar.value, priceVar.value, currency)) }
    val priceTF = CurrencyTextField(label = stringResource(R.string.manual_register_price),
      modifier = Modifier.constrainAs(price) {
        bottom.linkTo(button.top, margin = 8.dp)
        start.linkTo(parent.start, margin = 8.dp)
        width = Dimension.value(96.dp)
      },
      keyboardOptions = keyboardOptionsNumeric,
      onValueChange = {
        priceVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
        totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
      })
    val amountTF = TextField(label = stringResource(R.string.manual_register_amount),
      modifier = Modifier.constrainAs(amount) {
        bottom.linkTo(button.top, margin = 8.dp)
        start.linkTo(price.end, margin = 8.dp)
        width = Dimension.value(96.dp)
      },
      keyboardOptions = keyboardOptionsNumeric,
      textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
      onValueChange = {
        amountVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
        totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
      })
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
    Button(onClick = {
      manualRegisterViewModel.register(
        descriptionTF.getText(),
        categoryTF.getText(),
        placeTF.getText(),
        priceTF.getText(),
        amountTF.getText()
      )
    }, modifier = Modifier.constrainAs(button) {
      start.linkTo(parent.start)
      end.linkTo(parent.end)
      bottom.linkTo(parent.bottom, margin = 16.dp)
    }) {
      Text(text = stringResource(R.string.manual_register_register))
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
fun PreviewMessageCard() {
  ExpensesRegisterTheme {
    ManualRegisterComposable(
      ManualRegisterViewModel(), AppCompatActivity()
    )
  }
}
