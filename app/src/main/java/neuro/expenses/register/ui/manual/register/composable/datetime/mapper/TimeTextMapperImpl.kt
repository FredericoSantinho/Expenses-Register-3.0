package neuro.expenses.register.ui.manual.register.composable.datetime.mapper

class TimeTextMapperImpl : TimeTextMapper {
  override fun map(hour: Int, minute: Int): String {
    return hour.toString() + 'h' + minute.toString()
  }
}