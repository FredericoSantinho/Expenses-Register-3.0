package neuro.expenses.register.presentation.ui.common.composables.datetime

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.common.picker.date.OnSetDate
import neuro.expenses.register.presentation.common.picker.date.ShowDatePicker
import neuro.expenses.register.presentation.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.presentation.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.presentation.common.picker.time.OnSetTime
import neuro.expenses.register.presentation.common.picker.time.ShowTimePicker
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.DateTextMapper
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.DateTextMapperImpl
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.TimeTextMapper
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.TimeTextMapperImpl
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import java.util.*

@Composable
fun DateTimeComposable(
  fragmentActivity: FragmentActivity,
  modifier: Modifier = Modifier,
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl(),
  calendar: MutableState<Calendar> = mutableStateOf(Calendar.getInstance())
) {
  var hourVar = calendar.value.get(Calendar.HOUR_OF_DAY)
  var minuteVar = calendar.value.get(Calendar.MINUTE)
  var dayVar = calendar.value.get(Calendar.DAY_OF_MONTH)
  var monthVar = calendar.value.get(Calendar.MONTH) + 1
  var yearVar = calendar.value.get(Calendar.YEAR)

  calendar.value.set(yearVar, monthVar - 1, dayVar, hourVar, minuteVar, 0)

  var timeText by remember { mutableStateOf(timeTextMapper.map(hourVar, minuteVar)) }
  var dateText by remember { mutableStateOf(dateTextMapper.map(dayVar, monthVar, yearVar)) }

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Text(text = timeText, style = MaterialTheme.typography.subtitle1)
    IconButton(
      modifier = Modifier
        .width(24.dp + 8.dp * 2)
        .height(24.dp)
        .padding(start = 8.dp, end = 8.dp),
      onClick = {
        showTimePicker.showTimePicker(fragmentActivity, object : OnSetTime {
          override fun onSetTime(hour: Int, minute: Int) {
            hourVar = hour
            minuteVar = minute
            timeText = timeTextMapper.map(hour, minute)
            setCalendar(calendar, hour, minute)
          }
        })
      }) {
      Icon(
        painter = painterResource(id = R.drawable.ic_change_time_24),
        contentDescription = null,
        tint = MaterialTheme.colors.primary
      )
    }
    Text(text = dateText, style = MaterialTheme.typography.subtitle1)
    IconButton(
      modifier = Modifier
        .width(24.dp + 8.dp)
        .height(24.dp)
        .padding(start = 8.dp), onClick = {
        showDatePicker.showDatePicker(fragmentActivity, object : OnSetDate {
          override fun onSetDate(day: Int, month: Int, year: Int) {
            dayVar = day
            monthVar = month
            yearVar = year
            dateText = dateTextMapper.map(day, month, year)
          setCalendar(calendar, day, month, year)
        }
      })
    }) {
      Icon(
        painter = painterResource(R.drawable.ic_change_date_24),
        contentDescription = null,
        tint = MaterialTheme.colors.primary
      )
    }
  }
}

private fun setCalendar(calendar: MutableState<Calendar>, hour: Int, minute: Int) {
  val value = calendar.value
  val year = value.get(Calendar.YEAR)
  val month = value.get(Calendar.MONTH)
  val day = value.get(Calendar.DAY_OF_MONTH)
  calendar.value.set(year, month, day, hour, minute, 0)
}

private fun setCalendar(calendar: MutableState<Calendar>, day: Int, month: Int, year: Int) {
  val value = calendar.value
  val hour = value.get(Calendar.HOUR_OF_DAY)
  val minute = value.get(Calendar.MINUTE)
  calendar.value.set(year, month - 1, day, hour, minute, 0)
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  ExpensesRegisterTheme {
    DateTimeComposable(FragmentActivity())
  }
}