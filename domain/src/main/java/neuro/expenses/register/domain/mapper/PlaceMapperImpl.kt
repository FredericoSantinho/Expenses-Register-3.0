package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.entity.Place

class PlaceMapperImpl(
  private val productMapper: ProductMapper,
  private val latLngMapper: LatLngMapper
) : PlaceMapper {
  override fun map(place: Place): PlaceDto {
    val placeName = place.name
    val productDtos = place.products.map { productMapper.map(it) }
    val latLngDto = latLngMapper.map(place.latLng)

    return PlaceDto(placeName, productDtos, latLngDto)
  }

  override fun map(placeDto: PlaceDto): Place {
    val placeName = placeDto.name
    val productDtos = placeDto.products.map { productMapper.map(it) }
    val latLngDto = latLngMapper.map(placeDto.latLngDto)

    return Place(placeName, productDtos, latLngDto)
  }
}