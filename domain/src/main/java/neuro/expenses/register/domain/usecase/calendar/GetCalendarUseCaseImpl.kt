package neuro.expenses.register.domain.usecase.calendar

import java.util.*

class GetCalendarUseCaseImpl : GetCalendarUseCase {
  override fun getCalendar(): Calendar {
    return Calendar.getInstance()
  }
}