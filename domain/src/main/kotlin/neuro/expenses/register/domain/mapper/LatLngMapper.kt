package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.entity.model.LatLng

fun LatLng.toDomain(): LatLngDto = LatLngDto(latitude, longitude)

fun LatLngDto.toEntity(): LatLng = LatLng(latitude, longitude)