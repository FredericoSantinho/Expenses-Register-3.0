package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.latlng.LatLngModel
import neuro.expenses.register.domain.dto.LatLngDto

fun LatLngDto.toData(): LatLngModel = LatLngModel(latitude, longitude)

fun LatLngModel.toDomain(): LatLngDto = LatLngDto(latitude, longitude)