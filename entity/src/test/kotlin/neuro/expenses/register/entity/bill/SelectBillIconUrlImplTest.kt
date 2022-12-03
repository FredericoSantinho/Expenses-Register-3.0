package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.test.mocks.billItemMock
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.expenses.register.entity.test.mocks.productMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SelectBillIconUrlImplTest {
  @Test
  fun selectBillIconUrl() {
    val getBillIconUrl = SelectBillIconUrlImpl()
    val billItems = listOf(newBillItem(1), newBillItem(1), newBillItem(1))
    val expectedUrl = billItems.get(0).placeProduct.product.iconUrl

    assertEquals(expectedUrl, getBillIconUrl.selectIconUrl(billItems))
  }

  private fun newBillItem(id: Long) =
    billItemMock(id, placeProductMock(product = productMock(iconUrl = "icon$id")))
}