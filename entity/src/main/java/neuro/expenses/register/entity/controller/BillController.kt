package neuro.expenses.register.entity.controller

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.*

class BillController(
  private val getProductId: GetProductId,
  private val getCategoryId: GetCategoryId,
  private val calculateBillTotal: CalculateBillTotal
) {
  fun contains(bill: Bill, productDescription: String): Boolean {
    return bill.billItems.find { it.product.description == productDescription } != null
  }

  fun add(bill: Bill, expense: Expense): Single<Bill> {
    return Single.defer {
      val productDescription = expense.description
      if (contains(bill, productDescription)) {
        Single.fromCallable {
          val billItem = getBillItem(bill, productDescription)
          val billId = bill.id
          val billItemId = billItem.id
          val product = billItem.product
          val oldAmount = billItem.amount
          val newAmount = oldAmount + expense.amount
          val newBillItem = BillItem(billItemId, product, newAmount, newAmount * product.price)

          val billItems = buildList(bill, newBillItem)
          val iconUrl = billItems.get(0).product.iconUrl
          val total = calculateBillTotal.getTotal(billItems)
          Bill(billId, bill.place, bill.calendar, total, billItems, iconUrl)
        }
      } else {
        getProductId.getProductId(
          expense.description.lowercase(),
          expense.category.lowercase(),
          expense.price
        )
          .flatMap { productId ->
            getCategoryId.getCategoryId(expense.category.lowercase()).map { categoryId ->
              val product = Product(
                productId,
                expense.description,
                Category(categoryId, expense.category),
                expense.price,
                expense.amount
              )

              val newBillItem = BillItem(
                0, product,
                expense.amount,
                expense.price * expense.amount
              )
              val billItems = buildList(bill, newBillItem)
              val total = calculateBillTotal.getTotal(billItems)
              Bill(0, bill.place, bill.calendar, total, billItems)
            }
          }
      }
    }
  }

  private fun buildList(bill: Bill, newBillItem: BillItem): List<BillItem> {
    val description = newBillItem.product.description
    val list = mutableListOf<BillItem>()

    if (contains(bill, newBillItem.product.description)) {
      addExistent(bill, description, list, newBillItem)
    } else {
      addNonExistent(bill, list, newBillItem)
    }

    return list
  }

  private fun addExistent(
    bill: Bill,
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

  private fun addNonExistent(bill: Bill, list: MutableList<BillItem>, newBillItem: BillItem) {
    list.addAll(bill.billItems)
    list.add(newBillItem)
  }

  private fun getBillItem(bill: Bill, productDescription: String) =
    bill.billItems.find { it.product.description == productDescription }!!
}