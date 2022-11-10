package neuro.expenses.register.viewmodel.bill.mapper

import java.util.*

interface DateTimeMapper {
  fun mapTime(calendar: Calendar): String
  fun mapDate(calendar: Calendar): String
}