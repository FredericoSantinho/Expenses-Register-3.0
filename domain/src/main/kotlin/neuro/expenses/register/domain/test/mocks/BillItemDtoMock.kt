package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.dto.PlaceProductDto

fun billItemDtoMock(
  id: Long, placeProductDto: PlaceProductDto = placeProductDtoMock(id), amount: Double = 1.0
): BillItemDto {
  return BillItemDto(id, placeProductDto, amount)
}

fun billItemsDtoMock(): MutableList<BillItemDto> {
  return mutableListOf(billItemDtoMock(1), billItemDtoMock(2), billItemDtoMock(3))
}