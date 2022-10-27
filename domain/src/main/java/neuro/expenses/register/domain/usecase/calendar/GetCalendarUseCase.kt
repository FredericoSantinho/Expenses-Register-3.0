package neuro.expenses.register.domain.usecase.calendar

import java.util.*

interface GetCalendarUseCase {
  /**
   * @return If a bill is open, the bill's calendar, otherwise the current calendar.
   */
  fun getCalendar(): Calendar
}