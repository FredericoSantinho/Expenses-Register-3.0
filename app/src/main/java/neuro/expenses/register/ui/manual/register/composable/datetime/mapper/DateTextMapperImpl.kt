package neuro.expenses.register.ui.manual.register.composable.datetime.mapper

class DateTextMapperImpl : DateTextMapper {
  override fun map(day: Int, month: Int, year: Int): String {
    return day.toString() + '/' + month + '/' + year
  }
}