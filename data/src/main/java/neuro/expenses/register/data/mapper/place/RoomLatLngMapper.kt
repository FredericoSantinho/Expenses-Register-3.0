package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.LatLng
import neuro.expenses.register.domain.dto.LatLngDto

fun LatLngDto.toData(): LatLng = LatLng(latitude, longitude)

fun LatLng.toDomain(): LatLngDto = LatLngDto(latitude, longitude)