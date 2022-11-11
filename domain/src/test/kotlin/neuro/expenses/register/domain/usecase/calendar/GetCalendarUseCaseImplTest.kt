package neuro.expenses.register.domain.usecase.calendar

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

internal class GetCalendarUseCaseImplTest {
  @Test
  fun test() {
    val calendar = Calendar.getInstance()

    val getCalendarUseCase = GetCalendarUseCaseImpl(calendar)

    assertEquals(calendar, getCalendarUseCase.getCalendar())
    assertEquals(calendar, getCalendarUseCase.getCalendar())
  }
}