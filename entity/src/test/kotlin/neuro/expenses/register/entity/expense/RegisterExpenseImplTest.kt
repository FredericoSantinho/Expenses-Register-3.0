package neuro.expenses.register.entity.expense

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.bill.BillController
import neuro.expenses.register.entity.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.expense.validator.RegisterExpenseException
import neuro.expenses.register.entity.mocks.billMock
import neuro.expenses.register.entity.mocks.placeMock
import neuro.expenses.register.entity.mocks.placeProductMock
import neuro.expenses.register.entity.model.Bill
import neuro.expenses.register.entity.model.Expense
import neuro.expenses.register.entity.place.GetOrCreatePlace
import neuro.expenses.register.entity.place.PlaceController
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct
import neuro.test.rx.Incrementer
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
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(billController.add(expense)).thenReturn(
      Single.just(billMock()).observeSubscription(incrementer, offset)
    )
    val placeProduct = placeProductMock()
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, category, price, variableAmount, iconUrl
      )
    ).thenReturn(
      Single.just(placeProduct).observeSubscription(incrementer, offset)
    )
    val place = placeMock()
    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
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
        .observeSubscription(incrementer, offset)
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
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(billController.add(expense)).thenReturn(
      Single.just(billMock()).observeSubscription(incrementer, offset)
    )
    val placeProduct = placeProductMock()
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, category, price, variableAmount, iconUrl
      )
    ).thenReturn(
      Single.just(placeProduct).observeSubscription(incrementer, offset)
    )
    val place = placeMock()
    whenever(getOrCreatePlace.getOrCreatePlace(placeName)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )
    whenever(placeController.contains(place, placeProduct)).thenReturn(false)
    whenever(placeController.addPlaceProduct(place, placeProduct)).thenReturn(
      Single.just(place).observeSubscription(incrementer, offset)
    )

    registerExpense.registerExpense(expense).test().assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}