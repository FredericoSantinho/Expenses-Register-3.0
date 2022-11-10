package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.viewmodel.home.model.LatLngModel

class LatLngModelMapperImpl : LatLngModelMapper {
  override fun map(latLngDto: LatLngDto): LatLngModel {
    return LatLngModel(latLngDto.latitude, latLngDto.longitude)
  }
}