package neuro.expenses.register.domain.controller

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.BillItem
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.controller.CalculateBillTotal

internal class BillController(
  private val calculateBillTotal: CalculateBillTotal,
  private val productMapper: ProductMapper,
  var bill: Bill
) {
  fun contains(productDescription: String): Boolean {
    return bill.billItems.find { it.product.description == productDescription } != null
  }

  fun add(expense: Expense): Completable {
    return Completable.defer {
      val productDescription = expense.description
      if (contains(productDescription)) {
        Completable.fromAction {
          val billItem = getBillItem(productDescription)
          val billId = bill.id
          val billItemId = billItem.id
          val product = billItem.product
          val oldAmount = billItem.amount
          val newAmount = oldAmount + expense.amount
          val newBillItem = BillItem(billItemId, product, newAmount, newAmount * product.price)

          val billItems = buildList(newBillItem)
          val iconUrl = billItems.get(0).product.iconUrl
          val total = calculateBillTotal.getTotal(billItems)
          bill = Bill(billId, bill.place, bill.calendar, total, billItems, iconUrl)
        }
      } else {
        Completable.fromAction {
          val product = productMapper.map(0L, expense)

          val newBillItem = BillItem(
            0, product,
            expense.amount,
            expense.price * expense.amount
          )
          val billItems = buildList(newBillItem)
          val total = calculateBillTotal.getTotal(billItems)
          bill = Bill(0, bill.place, bill.calendar, total, billItems)
        }
      }
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