package neuro.expenses.register.presentation.ui.common.composables.datetime.mapper

import neuro.expenses.register.viewmodel.common.formatter.NumberFormater
import neuro.expenses.register.viewmodel.common.formatter.NumberFormaterImpl

class DateTextMapperImpl(private val numberFormater: NumberFormater = NumberFormaterImpl()) :
  DateTextMapper {
  override fun map(day: Int, month: Int, year: Int): String {
    return numberFormater.format(day) + '/' + numberFormater.format(month) + '/' + year
  }
}