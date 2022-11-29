package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.placeproduct.GetOrCreatePlaceProduct
import neuro.expenses.register.entity.mocks.*
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

internal class BillControllerImplTest : ObserveSubscriptionTest() {
  @Test
  fun addNonExistentBillItem() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val calculateBillTotal = mock<CalculateBillTotal>()
    val getBillIconUrl = mock<GetBillIconUrl>()
    val getLastBill = mock<GetLastBill>()
    val saveBill = mock<SaveBill>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val generateBillId = mock<GenerateBillId>()
    val generateBillItemId = mock<GenerateBillItemId>()

    val billController = BillControllerImpl(
      calculateBillTotal,
      getBillIconUrl,
      getLastBill,
      saveBill,
      getOrCreatePlaceProduct,
      getOrCreatePlace,
      generateBillId,
      generateBillItemId
    )

    val calendar = Calendar.getInstance()
    val placeName = "placeName"
    val place = placeMock(placeName = placeName)
    val amount = 2.0
    val lastBill = billMock(calendar = calendar, place = place)
    val billItems = billItemsMock()
    val billItemId = 4L
    billItems.add(billItemMock(billItemId, amount = amount))
    val bill = billMock(calendar = calendar, place = place, billItems = billItems)

    val description = "description"
    val category = "category"
    val price = 1.0
    val expense = expenseMock(
      description = description,
      category = category,
      place = placeName,
      price = price,
      amount = amount
    )
    val placeProduct = placeProductMock(4)

    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(getLastBill.getLastBill()).thenReturn(
      Maybe.just(lastBill).observeSubscription(incrementer, offset)
    )
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(description, category, price, false, "")
    ).thenReturn(Single.just(placeProduct).observeSubscription(incrementer, offset))
    whenever(generateBillItemId.newId()).thenReturn(
      Single.just(billItemId).observeSubscription(incrementer, offset)
    )
    whenever(calculateBillTotal.getTotal(billItems)).thenReturn(1.0)
    whenever(getBillIconUrl.getIconUrl(any())).thenReturn("")

    billController.add(expense).test().assertValue(bill).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addExistentBillItem() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val calculateBillTotal = mock<CalculateBillTotal>()
    val getBillIconUrl = mock<GetBillIconUrl>()
    val getLastBill = mock<GetLastBill>()
    val saveBill = mock<SaveBill>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val generateBillId = mock<GenerateBillId>()
    val generateBillItemId = mock<GenerateBillItemId>()

    val billController = BillControllerImpl(
      calculateBillTotal,
      getBillIconUrl,
      getLastBill,
      saveBill,
      getOrCreatePlaceProduct,
      getOrCreatePlace,
      generateBillId,
      generateBillItemId
    )

    val calendar = Calendar.getInstance()
    val description = "description 1"
    val placeName = "placeName"
    val place = placeMock(placeName = placeName)
    val lastBill = billMock(calendar = calendar, place = place)
    val placeProduct = placeProductMock(product = productMock(description = description))
    val billItems =
      mutableListOf(billItemMock(1, placeProduct, 3.0), billItemMock(2), billItemMock(3))
    val bill = billMock(calendar = calendar, place = place, billItems = billItems)

    val category = "category"
    val price = 1.0
    val amount = 2.0
    val expense = expenseMock(
      description = description,
      category = category,
      place = placeName,
      price = price,
      amount = amount
    )

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(description, category, price, false, "")
    ).thenReturn(Single.just(placeProduct).observeSubscription(incrementer, offset))
    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(getLastBill.getLastBill()).thenReturn(
      Maybe.just(lastBill).observeSubscription(incrementer, offset)
    )
    whenever(calculateBillTotal.getTotal(billItems)).thenReturn(1.0)
    whenever(getBillIconUrl.getIconUrl(any())).thenReturn("")

    billController.add(expense).test().assertValue(bill).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addVariableAmountProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val calculateBillTotal = mock<CalculateBillTotal>()
    val getBillIconUrl = mock<GetBillIconUrl>()
    val getLastBill = mock<GetLastBill>()
    val saveBill = mock<SaveBill>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val generateBillId = mock<GenerateBillId>()
    val generateBillItemId = mock<GenerateBillItemId>()

    val billController = BillControllerImpl(
      calculateBillTotal,
      getBillIconUrl,
      getLastBill,
      saveBill,
      getOrCreatePlaceProduct,
      getOrCreatePlace,
      generateBillId,
      generateBillItemId
    )

    val calendar = Calendar.getInstance()
    val placeName = "placeName"
    val place = placeMock(placeName = placeName)
    val amount = 0.2
    val lastBill = billMock(calendar = calendar, place = place)
    val billItems = billItemsMock()
    val billItemId = 4L
    billItems.add(billItemMock(billItemId, amount = amount))
    val bill = billMock(calendar = calendar, place = place, billItems = billItems)

    val description = "description"
    val category = "category"
    val price = 1.0
    val expense = expenseMock(
      description = description,
      category = category,
      place = placeName,
      price = price,
      amount = amount
    )
    val placeProduct = placeProductMock(4)

    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(getLastBill.getLastBill()).thenReturn(
      Maybe.just(lastBill).observeSubscription(incrementer, offset)
    )
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(description, category, price, true, "")
    ).thenReturn(Single.just(placeProduct).observeSubscription(incrementer, offset))
    whenever(generateBillItemId.newId()).thenReturn(
      Single.just(billItemId).observeSubscription(incrementer, offset)
    )
    whenever(calculateBillTotal.getTotal(billItems)).thenReturn(1.0)
    whenever(getBillIconUrl.getIconUrl(any())).thenReturn("")

    billController.add(expense).test().assertValue(bill).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addToClosedBill() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val calculateBillTotal = mock<CalculateBillTotal>()
    val getBillIconUrl = mock<GetBillIconUrl>()
    val getLastBill = mock<GetLastBill>()
    val saveBill = mock<SaveBill>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val generateBillId = mock<GenerateBillId>()
    val generateBillItemId = mock<GenerateBillItemId>()

    val billController = BillControllerImpl(
      calculateBillTotal,
      getBillIconUrl,
      getLastBill,
      saveBill,
      getOrCreatePlaceProduct,
      getOrCreatePlace,
      generateBillId,
      generateBillItemId
    )

    val calendar = Calendar.getInstance()
    val description = "description 1"
    val placeName = "placeName"
    val place = placeMock(placeName = placeName)
    val amount = 2.0
    val lastBill = billMock(calendar = calendar, place = place, isOpen = false)
    val placeProduct = placeProductMock(product = productMock(description = description))
    val billItems = mutableListOf(billItemMock(1, placeProduct, amount))
    val bill = billMock(calendar = calendar, place = place, billItems = billItems)

    val category = "category"
    val price = 1.0
    val expense = expenseMock(
      description = description,
      category = category,
      place = placeName,
      price = price,
      amount = amount,
      calendar = calendar
    )

    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(getLastBill.getLastBill()).thenReturn(
      Maybe.just(lastBill).observeSubscription(incrementer, offset)
    )
    whenever(calculateBillTotal.getTotal(billItems)).thenReturn(1.0)
    whenever(getBillIconUrl.getIconUrl(any())).thenReturn("")
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        any(), any(), any(), any(), any()
      )
    ).thenReturn(Single.just(placeProduct))
    whenever(generateBillId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )
    whenever(generateBillItemId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )

    billController.add(expense).test().assertValue(bill).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addDifferentPlace() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val calculateBillTotal = mock<CalculateBillTotal>()
    val getBillIconUrl = mock<GetBillIconUrl>()
    val getLastBill = mock<GetLastBill>()
    val saveBill = mock<SaveBill>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val generateBillId = mock<GenerateBillId>()
    val generateBillItemId = mock<GenerateBillItemId>()

    val billController = BillControllerImpl(
      calculateBillTotal,
      getBillIconUrl,
      getLastBill,
      saveBill,
      getOrCreatePlaceProduct,
      getOrCreatePlace,
      generateBillId,
      generateBillItemId
    )

    val calendar = Calendar.getInstance()
    val description = "description 1"
    val placeName = "placeName"
    val place = placeMock(2)
    val amount = 2.0
    val lastBill = billMock(calendar = calendar, place = placeMock(1), isOpen = true)
    val placeProduct = placeProductMock(product = productMock(description = description))
    val billItems = mutableListOf(billItemMock(1, placeProduct, amount))
    val bill = billMock(calendar = calendar, place = place, billItems = billItems)

    val category = "category"
    val price = 1.0
    val expense = expenseMock(
      description = description,
      category = category,
      place = placeName,
      price = price,
      amount = amount,
      calendar = calendar
    )

    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(getLastBill.getLastBill()).thenReturn(
      Maybe.just(lastBill).observeSubscription(incrementer, offset)
    )
    whenever(calculateBillTotal.getTotal(billItems)).thenReturn(1.0)
    whenever(getBillIconUrl.getIconUrl(any())).thenReturn("")
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        any(), any(), any(), any(), any()
      )
    ).thenReturn(Single.just(placeProduct))
    whenever(generateBillId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )
    whenever(generateBillItemId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )

    billController.add(expense).test().assertValue(bill).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}