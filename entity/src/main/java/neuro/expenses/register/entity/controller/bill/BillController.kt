package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.*
import neuro.expenses.register.entity.controller.category.GetCategoryId
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.product.GenerateProductId
import neuro.expenses.register.entity.controller.product.GetProduct
import neuro.expenses.register.entity.controller.product.SaveProduct
import java.util.*

private val defaultBillDto = Bill(0, Calendar.getInstance(), isOpen = false)

class BillController(
  private val getProduct: GetProduct,
  private val getCategoryId: GetCategoryId,
  private val calculateBillTotal: CalculateBillTotal,
  private val generateProductId: GenerateProductId,
  private val saveProduct: SaveProduct,
  private val getBillIconUrl: GetBillIconUrl,
  private val getLastBill: GetLastBill,
  private val saveBill: SaveBill,
  private val getOrCreatePlace: GetOrCreatePlace,
) {
  fun add(expense: Expense): Single<Bill> {
    return getLastBill(expense).flatMap { bill ->
      Single.defer {
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
            val total = calculateBillTotal.getTotal(billItems)
            val iconUrl = getBillIconUrl.getIconUrl(billItems)
            Bill(billId, bill.calendar, bill.place, total, billItems, iconUrl)
          }
        } else {
          getProduct.getProduct(
            expense.description.lowercase(),
            expense.category.lowercase(),
            expense.price
          ).switchIfEmpty(generateProductId.newId().flatMap { productId ->
            println("loosing ids $productId")
            getCategoryId.getCategoryId(expense.category.lowercase()).flatMap { categoryId ->
              val product = Product(
                productId,
                expense.description,
                Category(categoryId, expense.category),
                expense.price,
                expense.amount,
                bill.place.id
              )
              saveProduct.saveProduct(product).andThen(Single.just(product))
            }
          }
          ).map { product ->
            val newBillItem = BillItem(
              0, product,
              expense.amount,
              expense.price * expense.amount
            )
            val billItems = buildList(bill, newBillItem)
            val total = calculateBillTotal.getTotal(billItems)
            val iconUrl = getBillIconUrl.getIconUrl(billItems)
            Bill(0, bill.calendar, bill.place, total, billItems, iconUrl)
          }
        }
      }
    }.doOnSuccess { bill ->
      saveBill.save(bill)
    }
  }

  private fun contains(bill: Bill, productDescription: String): Boolean {
    return bill.billItems.find { it.product.description.lowercase() == productDescription.lowercase() } != null
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

  private fun getLastBill(expense: Expense): Single<Bill> {
    return getOrCreatePlace.getOrCreatePlace(expense.place).flatMap { place ->
      getLastBill.getLastBill().defaultIfEmpty(defaultBillDto).map { lastStoredBill ->
        val calendar = expense.calendar
        if (!lastStoredBill.isOpen || lastStoredBill.place.id != place.id) {
          newBill(calendar, place)
        } else {
          lastStoredBill
        }
      }
    }
  }

  private fun newBill(
    calendar: Calendar,
    place: Place
  ) = Bill(0, calendar, place)
}