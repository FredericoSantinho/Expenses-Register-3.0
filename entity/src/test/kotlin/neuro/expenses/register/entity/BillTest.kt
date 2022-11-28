package neuro.expenses.register.entity

import neuro.expenses.register.entity.mocks.billItemsMock
import neuro.expenses.register.entity.mocks.placeMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class BillTest {
  @Test
  fun test() {
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

    assertEquals(bill.id, id)
    assertEquals(bill.calendar, calendar)
    assertEquals(bill.place, place)
    assertEquals(bill.total, total)
    assertEquals(bill.billItems, billItems)
    assertEquals(bill.iconUrl, iconUrl)
    assertEquals(bill.isOpen, isOpen)

    assertEquals(bill, billEqual)
    assertNotEquals(bill, billDifferent)
  }

  @Test
  fun testBillWithEmptyPlace() {
    val id = 1L
    val calendar = Calendar.getInstance()

    val bill = Bill(id, calendar)

    assertEquals(bill.place.id, -1L)
  }
}