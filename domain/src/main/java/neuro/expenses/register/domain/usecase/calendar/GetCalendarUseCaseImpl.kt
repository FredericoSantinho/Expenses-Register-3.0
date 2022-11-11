package neuro.expenses.register.domain.usecase.calendar

import java.util.*

class GetCalendarUseCaseImpl(private val calendar: Calendar = Calendar.getInstance()) :
  GetCalendarUseCase {
  override fun getCalendar(): Calendar {
    return calendar
  }
}