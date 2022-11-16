package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.viewmodel.home.model.LatLngModel

fun LatLngDto.toViewModel(): LatLngModel = LatLngModel(latitude, longitude)