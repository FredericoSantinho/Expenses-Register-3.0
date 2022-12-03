package neuro.expenses.register.domain.usecase.calendar

import java.util.*

class GetCalendarUseCaseImpl(private val calendar: Calendar) :
  GetCalendarUseCase {
  override fun getCalendar(): Calendar {
    return calendar
  }
}