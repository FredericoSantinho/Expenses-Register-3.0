package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.LatLngModel
import neuro.expenses.register.domain.dto.LatLngDto

fun LatLngDto.toData(): LatLngModel = LatLngModel(latitude, longitude)

fun LatLngModel.toDomain(): LatLngDto = LatLngDto(latitude, longitude)