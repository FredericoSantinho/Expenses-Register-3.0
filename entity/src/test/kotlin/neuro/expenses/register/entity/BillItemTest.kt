package neuro.expenses.register.entity

import neuro.expenses.register.entity.mocks.placeProductMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class BillItemTest {

  @Test
  fun test() {
    val id = 1L
    val placeProduct = placeProductMock()
    val amount = 1.0
    val total = 1.0

    val billItem = BillItem(id, placeProduct, amount, total)
    val billItemEqual = BillItem(id, placeProduct, amount, total)
    val billItemDifferent = BillItem(id + 1, placeProduct, amount, total)

    assertEquals(billItem.id, id)
    assertEquals(billItem.placeProduct, placeProduct)
    assertEquals(billItem.amount, amount)
    assertEquals(billItem.total, total)

    assertEquals(billItem, billItemEqual)
    assertNotEquals(billItem, billItemDifferent)
  }
}