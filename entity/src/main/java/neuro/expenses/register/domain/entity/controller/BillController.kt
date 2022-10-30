package neuro.expenses.register.domain.entity.controller

import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.BillItem

class BillController(var bill: Bill) {
  fun contains(productDescription: String): Boolean {
    return bill.billItems.find { it.product.description == productDescription } != null
  }

  fun add(billItem: BillItem) {
    val productDescription = billItem.product.description
    if (contains(productDescription)) {
      val oldAmount = getBillItem(productDescription).amount
      val newBillItem = BillItem(billItem.product, oldAmount + billItem.amount)

      bill = Bill(bill.place, bill.timestamp, buildList(newBillItem))
    } else {
      bill = Bill(bill.place, bill.timestamp, buildList(billItem))
    }
  }

  private fun buildList(newBillItem: BillItem): List<BillItem> {
    val description = newBillItem.product.description
    val list = mutableListOf<BillItem>()

    if (contains(newBillItem.product.description)) {
      addExistent(description, list, newBillItem)
    } else {
      addNonExistent(list, newBillItem)
    }

    return list
  }

  private fun addExistent(
    description: String,
    list: MutableList<BillItem>,
    newBillItem: BillItem
  ) {
    bill.billItems.forEach {
      if (it.product.description == description) {
        list.add(newBillItem)
      } else {
        list.add(it)
      }
    }
  }

  private fun addNonExistent(
    list: MutableList<BillItem>,
    newBillItem: BillItem
  ) {
    list.addAll(bill.billItems)
    list.add(newBillItem)
  }

  private fun getBillItem(productDescription: String) =
    bill.billItems.find { it.product.description == productDescription }!!
}