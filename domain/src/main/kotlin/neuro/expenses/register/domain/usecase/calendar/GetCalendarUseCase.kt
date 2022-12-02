package neuro.expenses.register.domain.usecase.calendar

import java.util.*

interface GetCalendarUseCase {
  /**
   * @return the current calendar.
   */
  fun getCalendar(): Calendar
}