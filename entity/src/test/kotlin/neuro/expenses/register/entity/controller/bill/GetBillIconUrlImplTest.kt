package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.mocks.billItemMock
import neuro.expenses.register.entity.mocks.placeProductMock
import neuro.expenses.register.entity.mocks.productMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GetBillIconUrlImplTest {
  @Test
  fun test() {
    val getBillIconUrl = GetBillIconUrlImpl()
    val billItems = listOf(newBillItem(1), newBillItem(1), newBillItem(1))
    val expectedUrl = billItems.get(0).placeProduct.product.iconUrl
    assertEquals(expectedUrl, getBillIconUrl.getIconUrl(billItems))
  }

  private fun newBillItem(id: Long) =
    billItemMock(id, placeProductMock(product = productMock(iconUrl = "icon$id")))
}