package neuro.expenses.register.entity.model

import neuro.expenses.register.entity.test.mocks.placeProductMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BillItemTest {

  @Test
  fun billItem() {
    val id = 1L
    val placeProduct = placeProductMock()
    val amount = 1.0
    val total = 1.0

    val billItem = BillItem(id, placeProduct, amount, total)
    val billItemEqual = BillItem(id, placeProduct, amount, total)
    val billItemDifferent = BillItem(id + 1, placeProduct, amount, total)

    Assertions.assertEquals(billItem.id, id)
    Assertions.assertEquals(billItem.placeProduct, placeProduct)
    Assertions.assertEquals(billItem.amount, amount)
    Assertions.assertEquals(billItem.total, total)

    Assertions.assertEquals(billItem, billItemEqual)
    Assertions.assertNotEquals(billItem, billItemDifferent)
  }
}