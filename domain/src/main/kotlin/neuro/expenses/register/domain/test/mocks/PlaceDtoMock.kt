package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto

fun placeDtoMock(
  id: Long = 1L,
  placeName: String = "name $id",
  placeProductDtos: List<PlaceProductDto> = placeProductsDtoMock(),
  latLngDto: LatLngDto = latLngDtoMock()
): PlaceDto {
  return PlaceDto(id, placeName, placeProductDtos, latLngDto)
}