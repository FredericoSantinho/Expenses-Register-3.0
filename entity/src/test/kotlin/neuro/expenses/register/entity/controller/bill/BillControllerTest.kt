package neuro.expenses.register.entity.controller.bill

internal class BillControllerTest {
  //  @get:Rule
//  val koinTestRule = KoinTestRule.create {
//    modules(entityModule)
//  }
//
//  @Test
//  fun contains() {
//    val bill = Bill("place", 1, 1.0, listOf(BillItem(Product("desc", "cat", 1.0), 1.0)))
//    val calculateBillTotal = mock<CalculateBillTotal> {}
//    val billController = BillController(calculateBillTotal, bill)
//
//    Assert.assertTrue(billController.contains("desc"))
//    Assert.assertFalse(billController.contains("desc1"))
//  }
//
//  @Test
//  fun add() {
//    val billItem = BillItem(Product("desc", "cat", 1.0), 1.0)
//    val bill = Bill(
//      "place",
//      1, 2.0,
//      listOf(billItem, BillItem(Product("desc1", "cat", 1.0), 1.0))
//    )
//    val calculateBillTotal = mock<CalculateBillTotal> {
//      on { it.getTotal(any()) } doReturn 3.0
//    }
//    val billController = BillController(calculateBillTotal, bill)
//
//    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
//    Assert.assertEquals(1.0, billController.bill.billItems.get(0).amount, 0.0)
//    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
//    billController.add(billItem)
//    Assert.assertEquals("desc", billController.bill.billItems.get(0).product.description)
//    Assert.assertEquals(2.0, billController.bill.billItems.get(0).amount, 0.0)
//    Assert.assertEquals(1.0, billController.bill.billItems.get(1).amount, 0.0)
//    Assert.assertEquals(3.0, billController.bill.total, 0.0)
//  }
}