package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.entity.Place

interface PlaceMapper {
  fun map(place: Place): PlaceDto
  fun map(placeDto: PlaceDto): Place
}