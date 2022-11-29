package neuro.expenses.register.domain.usecase.calendar

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class GetCalendarUseCaseImplTest {
  @Test
  fun getCalendarUseCase() {
    val calendar = Calendar.getInstance()

    val getCalendarUseCase = GetCalendarUseCaseImpl(calendar)

    assertEquals(calendar, getCalendarUseCase.getCalendar())
  }
}