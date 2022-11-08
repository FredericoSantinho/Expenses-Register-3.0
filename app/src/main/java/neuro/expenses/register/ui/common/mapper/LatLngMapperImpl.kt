package neuro.expenses.register.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.domain.dto.LatLngDto

class LatLngMapperImpl : LatLngMapper {
  override fun map(latLngDto: LatLngDto): LatLng {
    return LatLng(latLngDto.latitude, latLngDto.longitude)
  }
}