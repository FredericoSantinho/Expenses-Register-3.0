package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.*
import neuro.expenses.register.entity.controller.category.GetCategoryId
import neuro.expenses.register.entity.controller.product.GenerateProductId
import neuro.expenses.register.entity.controller.product.GetProductId

class BillController(
  private val getProductId: GetProductId,
  private val getCategoryId: GetCategoryId,
  private val calculateBillTotal: CalculateBillTotal,
  private val generateProductId: GenerateProductId
) {
  fun contains(bill: Bill, productDescription: String): Boolean {
    return bill.billItems.find { it.product.description.lowercase() == productDescription.lowercase() } != null
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
        ).switchIfEmpty(generateProductId.newId())
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
    return if (contains(bill, newBillItem.product.description)) {
      addExistent(bill, newBillItem)
    } else {
      addNonExistent(bill, newBillItem)
    }
  }

  private fun addExistent(
    bill: Bill,
    newBillItem: BillItem
  ): MutableList<BillItem> {
    val list = mutableListOf<BillItem>()
    bill.billItems.forEach {
      if (it.product.id == newBillItem.product.id) {
        list.add(newBillItem)
      } else {
        list.add(it)
      }
    }
    return list
  }

  private fun addNonExistent(bill: Bill, newBillItem: BillItem): MutableList<BillItem> {
    val list = mutableListOf<BillItem>()
    list.addAll(bill.billItems)
    list.add(newBillItem)
    return list
  }

  private fun getBillItem(bill: Bill, productDescription: String) =
    bill.billItems.find { it.product.description == productDescription }!!
}