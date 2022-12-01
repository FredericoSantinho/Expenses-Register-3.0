package neuro.expenses.register.viewmodel.mock

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto

fun placeDtoMock(
  id: Long = 1L,
  placeName: String = "name $id",
  placeProducts: List<PlaceProductDto> = placeProductsMock()
): PlaceDto {
  return PlaceDto(id, placeName, placeProducts, latLngDtoMock())
}