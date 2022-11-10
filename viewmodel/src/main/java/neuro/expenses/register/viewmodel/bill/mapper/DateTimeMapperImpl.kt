package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.common.formatter.NumberFormater
import java.util.*

class DateTimeMapperImpl(private val numberFormater: NumberFormater) : DateTimeMapper {
  override fun mapTime(calendar: Calendar): String {
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return numberFormater.format(hour) + 'h' + numberFormater.format(minute)
  }

  override fun mapDate(calendar: Calendar): String {
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)

    return numberFormater.format(day) + '/' + numberFormater.format(month) + '/' + year
  }
}