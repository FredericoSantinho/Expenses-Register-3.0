package neuro.expenses.register.domain.controller

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.BillItem
import neuro.expenses.register.domain.entity.Expense
import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.usecase.product.GetOrCreateProductUseCase

internal class BillController(
  private val calculateBillTotal: CalculateBillTotal,
  private val getOrCreateProductUseCase: GetOrCreateProductUseCase,
  var bill: Bill
) {
  fun contains(productDescription: String): Boolean {
    return bill.billItems.find { it.product.description == productDescription } != null
  }

  fun add(expense: Expense): Completable {
    return Completable.defer {
      val productDescription = expense.description
      if (contains(productDescription)) {
        return@defer Completable.fromAction {
          val billItem = getBillItem(productDescription)
          val product = billItem.product
          val oldAmount = billItem.amount
          val newAmount = oldAmount + expense.amount
          val newBillItem = BillItem(product, newAmount, newAmount * product.price)

          val billItems = buildList(newBillItem)
          val total = calculateBillTotal.getTotal(billItems)
          bill = Bill(bill.place, bill.timestamp, total, billItems)
        }
      } else {
        return@defer getOrCreateProductUseCase.getOrCreateProduct(expense).doOnSuccess { product ->
          val newBillItem = BillItem(product, expense.amount, expense.price * expense.amount)
          val billItems = buildList(newBillItem)
          val total = calculateBillTotal.getTotal(billItems)
          bill = Bill(bill.place, bill.timestamp, total, billItems)
        }.ignoreElement()
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