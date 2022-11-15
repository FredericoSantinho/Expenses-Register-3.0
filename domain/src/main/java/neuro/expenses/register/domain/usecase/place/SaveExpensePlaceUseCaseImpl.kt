package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.PlaceController
import neuro.expenses.register.entity.controller.PlaceControllerImpl
import neuro.expenses.register.entity.converter.ExpenseConverter

class SaveExpensePlaceUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlaceUseCase: SavePlaceUseCase,
  private val placeMapper: PlaceMapper,
  private val expenseMapper: ExpenseMapper,
  private val expenseConverter: ExpenseConverter,
  private val placeController: PlaceController = PlaceControllerImpl()
) : SaveExpensePlaceUseCase {
  override fun saveExpensePlace(expenseDto: ExpenseDto): Completable {
    return getPlaceUseCase.getPlace(expenseDto.place).flatMapSingle { placeDto ->
      val expense = expenseMapper.map(expenseDto)
      expenseConverter.convertToProduct(expense).map { product -> addProduct(placeDto, product) }
    }.flatMapCompletable { placeDto -> savePlaceUseCase.savePlace(placeDto) }
  }

  private fun addProduct(placeDto: PlaceDto, product: Product): PlaceDto {
    return placeMapper.map(placeController.addProduct(placeMapper.map(placeDto), product))
  }
}