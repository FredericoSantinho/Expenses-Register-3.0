package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.mocks.billItemsMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CalculateBillTotalImplTest {
  @Test
  fun calculateBillTotal() {
    val billItems = billItemsMock()
    val expectedTotal = billItems.map { it.placeProduct.price * it.amount }.sum()

    val calculateBillTotal = CalculateBillTotalImpl()
    assertEquals(expectedTotal, calculateBillTotal.getTotal(billItems))
  }
}