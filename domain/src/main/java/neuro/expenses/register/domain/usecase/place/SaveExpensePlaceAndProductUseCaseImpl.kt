package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.PlaceMapper

class SaveExpensePlaceAndProductUseCaseImpl(
  private val savePlaceUseCase: SavePlaceUseCase,
  private val placeMapper: PlaceMapper
) :
  SaveExpensePlaceAndProductUseCase {
  override fun saveExpensePlaceAndProduct(expenseDto: ExpenseDto): Completable {
    return savePlaceUseCase.savePlace(placeMapper.map(expenseDto))
  }
}