package neuro.expenses.register.domain.entity.controller

import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.BillItem
import neuro.expenses.register.domain.entity.Product
import org.junit.Assert
import org.junit.Test


internal class BillControllerTest {
  @Test
  fun contains() {
    val bill = Bill("place", 1, listOf(BillItem(Product("desc", "cat", 1.0), 1.0)))
    val billController = BillController(bill)

    Assert.assertTrue(billController.contains("desc"))
    Assert.assertFalse(billController.contains("desc1"))
  }

  @Test
  fun add() {
    val billItem = BillItem(Product("desc", "cat", 1.0), 1.0)
    val bill = Bill(
      "place",
      1,
      listOf(billItem, BillItem(Product("desc1", "cat", 1.0), 1.0))
    )
    val billController = BillController(bill)

    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
    Assert.assertEquals(1.0, billController.bill.billItems.get(0).amount, 0.0)
    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
    billController.add(billItem)
    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
    Assert.assertEquals(2.0, billController.bill.billItems.get(0).amount, 0.0)
    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
  }
}