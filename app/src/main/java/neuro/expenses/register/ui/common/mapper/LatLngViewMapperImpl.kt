package neuro.expenses.register.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.viewmodel.home.model.LatLngModel

class LatLngViewMapperImpl : LatLngViewMapper {
  override fun map(latLngModel: LatLngModel): LatLng {
    return LatLng(latLngModel.latitude, latLngModel.longitude)
  }
}