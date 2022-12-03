package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.entity.mocks.billDtoMock
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class SortBillsImplTest {
  @Test
  fun sortBills() {

    val sortBills = SortBillsImpl()
    val calendar = Calendar.getInstance()

    val billDtos = listOf(
      billDtoMock(3, calendar = setHour(1, calendar)),
      billDtoMock(2, calendar = setHour(2, calendar)),
      billDtoMock(1, calendar = setHour(3, calendar))
    )
    val expectedSortedBillsDtos = listOf(
      billDtoMock(1, calendar = setHour(3, calendar)),
      billDtoMock(2, calendar = setHour(2, calendar)),
      billDtoMock(3, calendar = setHour(1, calendar))
    )

    assertEquals(expectedSortedBillsDtos, sortBills.sortBills(billDtos))
  }

  private fun setHour(i: Int, calendar: Calendar): Calendar {
    calendar.set(Calendar.HOUR, i)
    return calendar
  }
}
