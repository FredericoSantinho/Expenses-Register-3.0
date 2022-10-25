package neuro.expenses.register.ui.manual.register.composable.mapper

import java.util.*

class DateTextMapperImpl : DateTextMapper {
  override fun map(timestamp: Long): String {
    val date = Date(timestamp)
    val calendar = Calendar.getInstance()
    calendar.time = Date(timestamp)
    return calendar.get(Calendar.DAY_OF_MONTH)
      .toString() + '/' + (calendar.get(Calendar.MONTH) + 1) + '/' + calendar.get(Calendar.YEAR)
  }
}