package neuro.expenses.register.domain.usecase.calendar

import java.util.*

interface GetCalendarUseCase {
  /**
   * Get global Calendar.
   *
   * @return the global calendar.
   */
  fun getCalendar(): Calendar
}