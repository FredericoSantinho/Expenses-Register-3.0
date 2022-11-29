package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.BillItem
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.product.GetOrCreatePlaceProduct
import java.util.*

private val defaultBillDto = Bill(0, Calendar.getInstance(), isOpen = false)

class BillControllerImpl(
  private val calculateBillTotal: CalculateBillTotal,
  private val getBillIconUrl: GetBillIconUrl,
  private val getLastBill: GetLastBill,
  private val saveBill: SaveBill,
  private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct,
  private val getOrCreatePlace: GetOrCreatePlace,
  private val generateBillId: GenerateBillId,
  private val generateBillItemId: GenerateBillItemId
) : BillController {
  override fun add(expense: Expense): Single<Bill> {
    return getOrCreatePlace.getOrCreatePlace(expense.place).flatMap { place ->
      getLastBill(expense, place).flatMap { bill ->
        Single.defer {
          val productDescription = expense.description
          if (contains(bill, productDescription)) {
            Single.fromCallable {
              val billItem = getBillItem(bill, productDescription)
              val billId = bill.id
              val billItemId = billItem.id
              val product = billItem.placeProduct
              val oldAmount = billItem.amount
              val newAmount = oldAmount + expense.amount
              val newBillItem = BillItem(billItemId, product, newAmount, newAmount * product.price)

              val billItems = buildList(bill, newBillItem)
              val total = calculateBillTotal.getTotal(billItems)
              val iconUrl = getBillIconUrl.getIconUrl(billItems)
              Bill(billId, bill.calendar, bill.place, total, billItems, iconUrl)
            }
          } else {
            getOrCreatePlaceProduct.getOrCreatePlaceProduct(
              expense.description,
              expense.category,
              expense.price,
              expense.amount % (expense.amount.toInt()) != 0.0,
              ""
            ).flatMap { product ->
              generateBillItemId.newId().map { billItemId ->
                val newBillItem = BillItem(
                  billItemId, product,
                  expense.amount,
                  expense.price * expense.amount
                )
                buildList(bill, newBillItem)
              }.map { billItems ->
                val total = calculateBillTotal.getTotal(billItems)
                val iconUrl = getBillIconUrl.getIconUrl(billItems)
                Bill(bill.id, bill.calendar, bill.place, total, billItems, iconUrl)
              }
            }
          }
        }
      }.doOnSuccess { bill ->
        saveBill.save(bill)
      }
    }
  }

  private fun contains(bill: Bill, productDescription: String): Boolean {
    return bill.billItems.find { it.placeProduct.product.description.lowercase() == productDescription.lowercase() } != null
  }

  private fun buildList(bill: Bill, newBillItem: BillItem): List<BillItem> {
    return if (contains(bill, newBillItem.placeProduct.product.description)) {
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
      if (it.placeProduct.id == newBillItem.placeProduct.id) {
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
    bill.billItems.find { it.placeProduct.product.description == productDescription }!!

  private fun getLastBill(expense: Expense, place: Place): Single<Bill> {
    return getLastBill.getLastBill().defaultIfEmpty(defaultBillDto).flatMap { lastStoredBill ->
      val calendar = expense.calendar
      if (!lastStoredBill.isOpen || lastStoredBill.place.id != place.id) {
        newBill(calendar, place)
      } else {
        Single.just(lastStoredBill)
      }
    }
  }

  private fun newBill(
    calendar: Calendar,
    place: Place
  ): Single<Bill> {
    return generateBillId.newId().map { billId -> Bill(billId, calendar, place) }
  }
}