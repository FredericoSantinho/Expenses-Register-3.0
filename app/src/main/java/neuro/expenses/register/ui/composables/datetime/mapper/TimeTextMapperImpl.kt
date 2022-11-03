package neuro.expenses.register.ui.composables.datetime.mapper

import neuro.expenses.register.common.formatter.NumberFormater
import neuro.expenses.register.common.formatter.NumberFormaterImpl

class TimeTextMapperImpl(private val numberFormater: NumberFormater = NumberFormaterImpl()) :
  TimeTextMapper {
  override fun map(hour: Int, minute: Int): String {
    return numberFormater.format(hour) + 'h' + numberFormater.format(minute)
  }
}