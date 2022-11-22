package neuro.expenses.register.entity.controller.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.controller.bill.BillController
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.product.GetPlaceProduct

class RegisterExpenseImpl(
  private val billController: BillController,
  private val expenseValidator: ExpenseValidator,
  private val getPlaceProduct: GetPlaceProduct,
  private val getOrCreatePlace: GetOrCreatePlace,
  private val placeController: PlaceController
) : RegisterExpense {
  override fun registerExpense(expense: Expense): Completable {
    return expenseValidator.validate(expense).andThen(billController.add(expense))
      .flatMapCompletable { addPlaceProductToPlaceIfNeeded(expense) }
  }

  private fun addPlaceProductToPlaceIfNeeded(expense: Expense): Completable {
    return getPlaceProduct.getPlaceProduct(expense.description, expense.category, expense.price)
      .flatMapCompletable { placeProduct ->
        getOrCreatePlace.getOrCreatePlace(expense.place).flatMapCompletable { place ->
          if (!placeController.contains(place, placeProduct)) {
            placeController.addProduct(place, placeProduct).ignoreElement()
          } else {
            Completable.complete()
          }
        }
      }
  }
}