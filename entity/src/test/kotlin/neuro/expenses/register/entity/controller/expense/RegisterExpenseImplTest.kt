package neuro.expenses.register.entity.controller.expense

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.controller.bill.BillController
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseException
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.placeproduct.GetOrCreatePlaceProduct
import neuro.expenses.register.entity.mocks.billMock
import neuro.expenses.register.entity.mocks.placeMock
import neuro.expenses.register.entity.mocks.placeProductMock
import neuro.test.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import java.util.*

internal class RegisterExpenseImplTest : ObserveSubscriptionTest() {
  @Test
  fun validExpense() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val billController = mock<BillController>()
    val expenseValidator = mock<ExpenseValidator>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val placeController = mock<PlaceController>()

    val registerExpense = RegisterExpenseImpl(
      billController, expenseValidator, getOrCreatePlaceProduct, getOrCreatePlace, placeController
    )

    val description = "description"
    val category = "category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""

    val placeName = "place"
    val amount = 1.0
    val calendar = Calendar.getInstance()

    val expense = Expense(description, category, placeName, price, amount, calendar)
    whenever(expenseValidator.validate(expense)).thenReturn(
      Completable.complete().observeSubscription(incrementer.getAndIncrement(), offset)
    )
    whenever(billController.add(expense)).thenReturn(
      Single.just(billMock()).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    val placeProduct = placeProductMock()
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, category, price, variableAmount, iconUrl
      )
    ).thenReturn(
      Single.just(placeProduct).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    val place = placeMock()
    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    whenever(placeController.contains(place, placeProduct)).thenReturn(true)

    registerExpense.registerExpense(expense).test().assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun invalidExpense() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val billController = mock<BillController>()
    val expenseValidator = mock<ExpenseValidator>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val placeController = mock<PlaceController>()

    val registerExpense = RegisterExpenseImpl(
      billController, expenseValidator, getOrCreatePlaceProduct, getOrCreatePlace, placeController
    )

    val description = "description"
    val category = "category"
    val price = 1.0

    val placeName = "place"
    val amount = 1.0
    val calendar = Calendar.getInstance()

    val expense = Expense(description, category, placeName, price, amount, calendar)
    whenever(expenseValidator.validate(expense)).thenReturn(
      Completable.error(RegisterExpenseException(emptySet()))
        .observeSubscription(incrementer.getAndIncrement(), offset)
    )
    whenever(billController.add(expense)).thenReturn(
      Maybe.empty<Bill>().toSingle()
    )

    registerExpense.registerExpense(expense).test().assertNoValues()
      .assertError(RegisterExpenseException::class.java).assertNotComplete()

    verifyNoInteractions(getOrCreatePlaceProduct)
    verifyNoInteractions(getOrCreatePlace)
    verifyNoInteractions(placeController)

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addExpenseToPlace() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val billController = mock<BillController>()
    val expenseValidator = mock<ExpenseValidator>()
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val getOrCreatePlace = mock<GetOrCreatePlace>()
    val placeController = mock<PlaceController>()

    val registerExpense = RegisterExpenseImpl(
      billController, expenseValidator, getOrCreatePlaceProduct, getOrCreatePlace, placeController
    )

    val description = "description"
    val category = "category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""

    val placeName = "place"
    val amount = 1.0
    val calendar = Calendar.getInstance()

    val expense = Expense(description, category, placeName, price, amount, calendar)
    whenever(expenseValidator.validate(expense)).thenReturn(
      Completable.complete().observeSubscription(incrementer.getAndIncrement(), offset)
    )
    whenever(billController.add(expense)).thenReturn(
      Single.just(billMock()).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    val placeProduct = placeProductMock()
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, category, price, variableAmount, iconUrl
      )
    ).thenReturn(
      Single.just(placeProduct).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    val place = placeMock()
    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer.getAndIncrement(), offset)
    )
    whenever(placeController.contains(place, placeProduct)).thenReturn(false)
    whenever(placeController.addPlaceProduct(place, placeProduct)).thenReturn(
      Single.just(place).observeSubscription(incrementer.getAndIncrement(), offset)
    )

    registerExpense.registerExpense(expense).test().assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}