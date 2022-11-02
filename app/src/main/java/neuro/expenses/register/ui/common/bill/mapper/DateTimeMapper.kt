package neuro.expenses.register.ui.common.bill.mapper

interface DateTimeMapper {
  fun mapTime(timestamp: Long): String
  fun mapDate(timestamp: Long): String
}