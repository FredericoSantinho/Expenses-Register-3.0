package neuro.expenses.register.domain.entity.controller

import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.BillItem
import neuro.expenses.register.domain.entity.Product
import neuro.expenses.register.domain.entity.di.entityModule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class BillControllerTest : KoinTest {
  @get:Rule
  val koinTestRule = KoinTestRule.create {
    modules(entityModule)
  }

  @Test
  fun contains() {
    val bill = Bill("place", 1, 1.0, listOf(BillItem(Product("desc", "cat", 1.0), 1.0)))
    val calculateBillTotal = mock<CalculateBillTotal> {}
    val billController = BillController(calculateBillTotal, bill)

    Assert.assertTrue(billController.contains("desc"))
    Assert.assertFalse(billController.contains("desc1"))
  }

  @Test
  fun add() {
    val billItem = BillItem(Product("desc", "cat", 1.0), 1.0)
    val bill = Bill(
      "place",
      1, 2.0,
      listOf(billItem, BillItem(Product("desc1", "cat", 1.0), 1.0))
    )
    val calculateBillTotal = mock<CalculateBillTotal> {
      on { it.getTotal(any()) } doReturn 3.0
    }
    val billController = BillController(calculateBillTotal, bill)

    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
    Assert.assertEquals(1.0, billController.bill.billItems.get(0).amount, 0.0)
    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
    billController.add(billItem)
    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
    Assert.assertEquals(2.0, billController.bill.billItems.get(0).amount, 0.0)
    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
    Assert.assertEquals(3.0, billController.bill.total, 0.0)
  }
}