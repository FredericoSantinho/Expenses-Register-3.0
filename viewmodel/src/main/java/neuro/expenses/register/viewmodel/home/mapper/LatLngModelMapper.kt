package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.viewmodel.home.model.LatLngModel

interface LatLngModelMapper {
  fun map(latLngDto: LatLngDto): LatLngModel
}