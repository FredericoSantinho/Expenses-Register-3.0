package neuro.expenses.register.ui.manual.register.mapper

interface DateTimeMapper {
  fun mapTime(timestamp: Long): String
  fun mapDate(timestamp: Long): String
}