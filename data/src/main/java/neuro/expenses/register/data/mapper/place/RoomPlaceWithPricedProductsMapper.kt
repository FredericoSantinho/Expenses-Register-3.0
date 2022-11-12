package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.RoomPlaceWithPricedProducts
import neuro.expenses.register.domain.dto.PlaceDto

interface RoomPlaceWithPricedProductsMapper {
  fun map(roomPlaceWithPricedProducts: RoomPlaceWithPricedProducts): PlaceDto
  fun map(roomPlaceWithPricedProductslist: List<RoomPlaceWithPricedProducts>): List<PlaceDto>
}