package neuro.expenses.register.entity.model

import neuro.expenses.register.entity.mocks.billItemsMock
import neuro.expenses.register.entity.mocks.placeMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class BillTest {
  @Test
  fun bill() {
    val id = 1L
    val calendar = Calendar.getInstance()
    val place: Place = placeMock()
    val total = 0.0
    val billItems: List<BillItem> = billItemsMock()
    val iconUrl = "iconUrl"
    val isOpen = false

    val bill = Bill(id, calendar, place, total, billItems, iconUrl, isOpen)
    val billEqual = Bill(id, calendar, place, total, billItems, iconUrl, isOpen)
    val billDifferent = Bill(id + 1, calendar, place, total, billItems, iconUrl, isOpen)

    Assertions.assertEquals(bill.id, id)
    Assertions.assertEquals(bill.calendar, calendar)
    Assertions.assertEquals(bill.place, place)
    Assertions.assertEquals(bill.total, total)
    Assertions.assertEquals(bill.billItems, billItems)
    Assertions.assertEquals(bill.iconUrl, iconUrl)
    Assertions.assertEquals(bill.isOpen, isOpen)

    Assertions.assertEquals(bill, billEqual)
    Assertions.assertNotEquals(bill, billDifferent)
  }

  @Test
  fun billWithEmptyPlace() {
    val id = 1L
    val calendar = Calendar.getInstance()

    val bill = Bill(id, calendar)

    Assertions.assertEquals(bill.place.id, -1L)
  }
}