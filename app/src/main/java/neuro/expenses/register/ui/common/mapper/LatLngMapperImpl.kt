package neuro.expenses.register.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.viewmodel.home.model.LatLngModel

class LatLngMapperImpl : LatLngMapper {
  override fun map(latLngModel: LatLngModel): LatLng {
    return LatLng(latLngModel.latitude, latLngModel.longitude)
  }
}