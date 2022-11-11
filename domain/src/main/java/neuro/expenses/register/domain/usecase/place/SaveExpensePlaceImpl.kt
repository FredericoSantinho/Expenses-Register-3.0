package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.entity.converter.ExpenseConverter

class SaveExpensePlaceImpl(
  private val savePlaceUseCase: SavePlaceUseCase,
  private val placeMapper: PlaceMapper,
  private val expenseMapper: ExpenseMapper,
  private val expenseConverter: ExpenseConverter
) :
  SaveExpensePlace {
  override fun saveExpensePlace(expenseDto: ExpenseDto): Completable {
    val expense = expenseMapper.map(expenseDto)
    val place = expenseConverter.convertToPlace(expense)
    val placeDto = placeMapper.map(place)

    return savePlaceUseCase.savePlace(placeDto)
  }
}