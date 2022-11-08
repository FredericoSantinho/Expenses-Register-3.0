package neuro.expenses.register.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.domain.dto.LatLngDto

interface LatLngMapper {
  fun map(latLngDto: LatLngDto): LatLng
}