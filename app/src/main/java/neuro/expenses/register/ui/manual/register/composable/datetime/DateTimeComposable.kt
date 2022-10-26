package neuro.expenses.register.ui.manual.register.composable.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R
import neuro.expenses.register.common.picker.date.OnSetDate
import neuro.expenses.register.common.picker.date.ShowDatePicker
import neuro.expenses.register.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.common.picker.time.OnSetTime
import neuro.expenses.register.common.picker.time.ShowTimePicker
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.DateTextMapperImpl
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapper
import neuro.expenses.register.ui.manual.register.composable.mapper.TimeTextMapperImpl
import java.util.*

@Composable
fun DateTimeComposable(
  appCompatActivity: FragmentActivity,
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl(),
  modifier: Modifier = Modifier
): DateTimeGetter {
  val calendar: Calendar = Calendar.getInstance()
  var hourVar = calendar.get(Calendar.HOUR_OF_DAY)
  var minuteVar = calendar.get(Calendar.MINUTE)
  var dayVar = calendar.get(Calendar.DAY_OF_MONTH)
  var monthVar = calendar.get(Calendar.MONTH) + 1
  var yearVar = calendar.get(Calendar.YEAR)

  var timeText by remember { mutableStateOf(timeTextMapper.map(hourVar, minuteVar)) }
  var dateText by remember { mutableStateOf(dateTextMapper.map(dayVar, monthVar, yearVar)) }

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Text(text = timeText)
    IconButton(onClick = {
      showTimePicker.showTimePicker(appCompatActivity, object : OnSetTime {
        override fun onSetTime(hour: Int, minute: Int) {
          hourVar = hour
          minuteVar = minute
          timeText = timeTextMapper.map(hour, minute)
        }
      })
    }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_change_time_24),
        contentDescription = null,
        tint = MaterialTheme.colors.primary
      )
    }
    Text(text = dateText)
    IconButton(onClick = {
      showDatePicker.showDatePicker(appCompatActivity, object : OnSetDate {
        override fun onSetDate(day: Int, month: Int, year: Int) {
          dayVar = day
          monthVar = month
          yearVar = year
          dateText = dateTextMapper.map(day, month, year)
        }
      })
    }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_change_date_24),
        contentDescription = null,
        tint = MaterialTheme.colors.primary
      )
    }
  }
  return object : DateTimeGetter {
    override fun getDateTime(): DateTime =
      DateTime(Date(dayVar, monthVar, yearVar), Time(hourVar, minuteVar))
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  ExpensesRegisterTheme {
    DateTimeComposable(FragmentActivity())
  }
}