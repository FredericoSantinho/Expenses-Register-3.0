package neuro.expenses.register.ui.common.bill.mapper

import java.util.*

interface DateTimeMapper {
  fun mapTime(calendar: Calendar): String
  fun mapDate(calendar: Calendar): String
}